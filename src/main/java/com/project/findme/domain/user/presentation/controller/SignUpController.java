package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.SignUpAddressRequest;
import com.project.findme.domain.user.presentation.dto.SignUpIdRequest;
import com.project.findme.domain.user.presentation.dto.SignUpNameRequest;
import com.project.findme.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("id")
    public ResponseEntity<Void> signUpId(@RequestBody @Valid SignUpIdRequest signUpIdRequest) {
        signUpService.signUpId(signUpIdRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("name")
    public ResponseEntity<Void> signUpName(@RequestBody @Valid SignUpNameRequest signUpNameRequest) {
        signUpService.signUpName(signUpNameRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("address")
    public ResponseEntity<Void> signUpAddress(@RequestBody @Valid SignUpAddressRequest signUpAddressRequest) {
        signUpService.signUpAddress(signUpAddressRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
