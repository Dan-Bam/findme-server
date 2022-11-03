package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;
import com.project.findme.domain.user.presentation.dto.SignUpRequest;

public interface UserService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);
    ReissueTokenResponse reissueToken(String token);
    void logout();

}
