package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.request.SignInRequest;
import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.response.SignInResponse;

public interface UserAccountService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);
    ReissueTokenResponse reissueToken(String refreshToken);


}
