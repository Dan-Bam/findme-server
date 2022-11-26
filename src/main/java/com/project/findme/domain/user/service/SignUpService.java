package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.request.SignUpRequest;

public interface SignUpService {

    void signUp(SignUpRequest signUpRequest);

}
