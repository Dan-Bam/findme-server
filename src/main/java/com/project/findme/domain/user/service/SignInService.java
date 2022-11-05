package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.SignInRequest;
import com.project.findme.domain.user.presentation.dto.SignInResponse;

public interface SignInService {

    SignInResponse signIn(SignInRequest signInRequest);

}
