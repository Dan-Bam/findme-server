package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.SignUpAddressRequest;
import com.project.findme.domain.user.presentation.dto.SignUpIdRequest;
import com.project.findme.domain.user.presentation.dto.SignUpNameRequest;

public interface SignUpService {

    void signUpId(SignUpIdRequest signUpIdRequest);
    void signUpName(SignUpNameRequest signUpNameRequest);
    void signUpAddress(SignUpAddressRequest signUpAddressRequest);

}
