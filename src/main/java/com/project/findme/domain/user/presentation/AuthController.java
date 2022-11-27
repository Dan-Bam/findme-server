package com.project.findme.domain.user.presentation;

import com.project.findme.domain.user.presentation.dto.request.SignInRequest;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.response.SignInResponse;
import com.project.findme.domain.user.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userAccountService.signUp(signUpRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return new ResponseEntity<>(userAccountService.signIn(signInRequest), HttpStatus.OK);
    }

    @PatchMapping("reissue")
    public ResponseEntity<ReissueTokenResponse> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        return new ResponseEntity<>(userAccountService.reissueToken(refreshToken), HttpStatus.OK);
    }

}
