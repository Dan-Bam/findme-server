package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.request.SignInRequest;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.response.SignInResponse;
import com.project.findme.domain.user.service.ReissueTokenService;
import com.project.findme.domain.user.service.SignInService;
import com.project.findme.domain.user.service.SignUpService;
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
    private final ReissueTokenService reissueTokenService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        signUpService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse token = signInService.signIn(signInRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PatchMapping("reissue")
    public ResponseEntity<ReissueTokenResponse> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        ReissueTokenResponse newToken = reissueTokenService.reissueToken(refreshToken);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

}
