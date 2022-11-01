package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;
import com.project.findme.domain.user.presentation.dto.SignUpRequest;
import com.project.findme.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse token = userService.signIn(signInRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PatchMapping("refresh")
    public ResponseEntity<ReissueTokenResponse> reissueToken(@RequestHeader("RefreshToken") String token) {
        ReissueTokenResponse newToken = userService.reissueToken(token);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

}