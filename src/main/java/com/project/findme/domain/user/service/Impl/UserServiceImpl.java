package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.InvalidTokenException;
import com.project.findme.domain.user.exception.RefreshTokenExpiredException;
import com.project.findme.domain.user.facade.UserFacade;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.service.UserService;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ReissueTokenResponse reissueToken(String refreshToken) {
        if(jwtTokenProvider.isExpired(refreshToken)) {
            throw new RefreshTokenExpiredException();
        }

        User user = userFacade.findUserById(jwtTokenProvider.getUserId(refreshToken));

        if(!user.getRefreshToken().equals(refreshToken)) {
            throw new InvalidTokenException();
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        user.updateRefreshToken(newRefreshToken);

        return ReissueTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout() {
        User user = userFacade.currentUser();
        user.updateRefreshToken(null);
    }

}
