package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.SignInDtoRequest;
import com.project.findme.domain.user.presentation.dto.SignUpRequest;
import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.SignInResponse;

public interface UserService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInDtoRequest signInDto);
    ReissueTokenResponse reissueToken(String token);
    void logout();
}
