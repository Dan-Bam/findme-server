package com.project.findme.domain.user.service;

import com.project.findme.domain.user.presentation.dto.response.ReissueTokenResponse;

public interface ReissueTokenService {

    ReissueTokenResponse reissueToken(String refreshToken);

}
