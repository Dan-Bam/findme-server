package com.project.findme.domain.user.presentation.controller;

import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reissue")
@RequiredArgsConstructor
public class ReissueTokenController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<ReissueTokenResponse> reissueToken(@RequestHeader("RefreshToken") String token) {
        ReissueTokenResponse newToken = userService.reissueToken(token);
        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }

}