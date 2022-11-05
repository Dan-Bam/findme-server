package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.IdNotFoundException;
import com.project.findme.domain.user.exception.PasswordNotMatchException;
import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;
import com.project.findme.domain.user.repository.UserRepository;
import com.project.findme.domain.user.service.SignInService;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignInResponse signIn(SignInRequest signInRequest) {

        User user = userRepository.findById(signInRequest.getId()).orElseThrow(() -> new IdNotFoundException("아이디를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        user.updateRefreshToken(refreshToken);

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
