package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.DuplicateIdException;
import com.project.findme.domain.user.exception.InvalidTokenException;
import com.project.findme.domain.user.exception.RefreshTokenExpiredException;
import com.project.findme.domain.user.facade.UserFacade;
import com.project.findme.domain.user.presentation.dto.request.SignInRequest;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.response.SignInResponse;
import com.project.findme.domain.user.service.UserAccountService;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signUpRequest) {
        if(userFacade.existsById(signUpRequest.getId())) {
            throw new DuplicateIdException();
        }
        userFacade.saveUser(signUpRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignInResponse signIn(SignInRequest signInRequest) {

        User user = userFacade.findUserById(signInRequest.getId());

        userFacade.checkPassword(user, signInRequest.getPassword());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        user.updateRefreshToken(refreshToken);

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();

    }

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
