package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.request.SignUpDto;
import com.project.findme.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<Void> signup(SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
