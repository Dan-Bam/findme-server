package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;
import com.project.findme.domain.user.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("signin")
@RequiredArgsConstructor
public class SigInController {

    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse token = signInService.signIn(signInRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
