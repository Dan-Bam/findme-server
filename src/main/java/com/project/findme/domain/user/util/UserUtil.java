package com.project.findme.domain.user.util;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.exception.UserNotFoundException;
import com.project.findme.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User currentUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
