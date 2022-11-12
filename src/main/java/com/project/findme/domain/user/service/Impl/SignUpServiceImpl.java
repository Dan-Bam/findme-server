package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.exception.DuplicateIdException;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.repository.UserRepository;
import com.project.findme.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUpId(SignUpRequest signUpRequest) {
        if (userRepository.findById(signUpRequest.getId()).isPresent()) {
            throw new DuplicateIdException("중복된 아이디가 존재 합니다.");
        }
        userRepository.save(signUpRequest.toEntity(passwordEncoder.encode(signUpRequest.getPassword())));
    }

}
