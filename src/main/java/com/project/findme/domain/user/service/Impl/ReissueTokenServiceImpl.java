package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.InvalidTokenException;
import com.project.findme.domain.user.exception.RefreshTokenExpiredException;
import com.project.findme.domain.user.facade.UserFacade;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.service.ReissueTokenService;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
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

}
