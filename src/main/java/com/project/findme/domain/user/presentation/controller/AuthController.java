package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;
import com.project.findme.domain.user.presentation.dto.SignUpRequest;
import com.project.findme.domain.user.service.SignInService;
import com.project.findme.domain.user.service.SignUpService;
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

    private final SignUpService signUpService;
    private final SignInService signInService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        signUpService.signUpId(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse token = signInService.signIn(signInRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PatchMapping("reissue")
    public ResponseEntity<ReissueTokenResponse> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        ReissueTokenResponse newToken = userService.reissueToken(refreshToken);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

}
