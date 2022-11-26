package com.project.findme.domain.user.facade;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.PasswordNotMatchException;
import com.project.findme.domain.user.exception.UserNotFoundException;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SignUpRequest signUpRequest) {
        userRepository.save(signUpRequest.toEntity(passwordEncoder.encode(signUpRequest.getPassword())));
    }

    @Transactional(rollbackFor = Exception.class)
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(rollbackFor = Exception.class)
    public void checkPassword(User user, String password) {
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchException();
        }
    }

    public User currentUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return findUserById(id);
    }

}
