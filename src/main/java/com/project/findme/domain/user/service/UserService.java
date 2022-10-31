package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.request.SignInDto;
import com.project.findme.domain.user.presentation.dto.request.SignUpDto;
import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;
import com.project.findme.domain.user.presentation.dto.response.SignInResponseDto;

public interface UserService {

    void signUp(SignUpDto signUpDto);
    SignInResponseDto signIn(SignInDto signInDto);
    ReissueTokenResponse reissueToken(String token);
    void logout();
}
