package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.PasswordNotMatchException;
import com.project.findme.domain.user.exception.UserNotFoundException;
import com.project.findme.domain.user.presentation.dto.request.SignInDto;
import com.project.findme.domain.user.presentation.dto.request.SignUpDto;
import com.project.findme.domain.user.presentation.dto.response.SignInResponseDto;
import com.project.findme.domain.user.repository.UserRepository;
import com.project.findme.domain.user.service.UserService;
import com.project.findme.domain.user.exception.DuplicateIdException;
import com.project.findme.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpDto signUpDto) {

        userRepository.findById(signUpDto.getId()).orElseThrow(() -> new DuplicateIdException("중복된 아이디 입니다."));
        userRepository.save(signUpDto.toEntity(passwordEncoder.encode(signUpDto.getPassword())));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignInResponseDto signIn(SignInDto signInDto) {

        User user = userRepository.findById(signInDto.getId()).orElseThrow(() -> new UserNotFoundException("아이디를 찾을 수 없습니다다."));
        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        user.updateRefreshToken(refreshToken);

        return SignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }


}
