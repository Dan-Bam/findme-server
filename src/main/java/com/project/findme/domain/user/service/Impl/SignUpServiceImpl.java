package com.project.findme.domain.user.service.Impl;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.DuplicateIdException;
import com.project.findme.domain.user.exception.IdNotFoundException;
import com.project.findme.domain.user.presentation.dto.SignUpAddressRequest;
import com.project.findme.domain.user.presentation.dto.SignUpIdRequest;
import com.project.findme.domain.user.presentation.dto.SignUpNameRequest;
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
    public void signUpId(SignUpIdRequest signUpIdRequest) {
        if (userRepository.findById(signUpIdRequest.getId()).isPresent()) {
            throw new DuplicateIdException("중복된 아이디가 존재 합니다.");
        }
        userRepository.save(signUpIdRequest.toEntity(passwordEncoder.encode(signUpIdRequest.getPassword())));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUpName(SignUpNameRequest signUpNameRequest) {
        User user = findById(signUpNameRequest.getId());
        user.updateUserName(signUpNameRequest.getUserName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUpAddress(SignUpAddressRequest signUpAddressRequest) {
        User user = findById(signUpAddressRequest.getId());
        user.updateAddress(signUpAddressRequest.getAddress());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("아이디를 찾을 수 없습니다."));
    }
}
