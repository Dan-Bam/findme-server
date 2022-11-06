package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.InvalidTokenException;
import com.project.findme.domain.user.exception.RefreshTokenExpiredException;
import com.project.findme.domain.user.exception.UserNotFoundException;
import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.repository.UserRepository;
import com.project.findme.domain.user.service.UserService;
import com.project.findme.domain.user.util.UserUtil;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserUtil userUtil;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ReissueTokenResponse reissueToken(String refreshToken) {
        if(jwtTokenProvider.isExpired(refreshToken)) {
            throw new RefreshTokenExpiredException("refreshToken이 만료되었습니다.");
        }

        User user = userRepository.findById(jwtTokenProvider.getUserId(refreshToken))
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        if(!user.getRefreshToken().equals(refreshToken)) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        user.updateRefreshToken(newRefreshToken);

        return ReissueTokenResponse.builder()
                .newAccessToken(newAccessToken)
                .newRefreshToken(newRefreshToken)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout() {
        User user = userUtil.currentUser();
        user.updateRefreshToken(null);
    }

}
