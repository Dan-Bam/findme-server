package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.*;
import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;
import com.project.findme.domain.user.presentation.dto.SignUpRequest;
import com.project.findme.domain.user.repository.UserRepository;
import com.project.findme.domain.user.service.UserService;
import com.project.findme.domain.user.util.UserUtil;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserUtil userUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signUpRequest) {

        if(userRepository.findById(signUpRequest.getId()).isPresent()) {
            throw new DuplicateIdException("중복된 아이디 입니다.");
        }

        userRepository.save(signUpRequest.toEntity(passwordEncoder.encode(signUpRequest.getPassword())));

    }

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

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ReissueTokenResponse reissueToken(String token) {
        if(!jwtTokenProvider.isExpired(token)) {
            throw new RefreshTokenExpiredException("refreshToken이 만료되었습니다.");
        }

        User user = userRepository.findById(jwtTokenProvider.getUserId(token))
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        return ReissueTokenResponse.builder()
                .newRefreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout() {
        User user = userUtil.currentUser();
        user.updateRefreshToken(null);
    }

}
