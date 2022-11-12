package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.request.SignInRequest;
import com.project.findme.domain.user.presentation.dto.response.SignInResponse;

public interface SignInService {

    SignInResponse signIn(SignInRequest signInRequest);

}
