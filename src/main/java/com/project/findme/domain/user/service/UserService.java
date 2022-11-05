package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.ReissueTokenResponse;

public interface UserService {

    ReissueTokenResponse reissueToken(String token);
    void logout();

}
