package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.request.SignInDto;
import com.project.findme.domain.user.presentation.dto.request.SignUpDto;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.response.SignInResponseDto;
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
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInDto signInDto) {
        SignInResponseDto result = userService.signIn(signInDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("refresh")
    public ResponseEntity<ReissueTokenResponse> reissueToken(@RequestHeader("RefreshToken") String token) {
        ReissueTokenResponse newToken = userService.reissueToken(token);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

}