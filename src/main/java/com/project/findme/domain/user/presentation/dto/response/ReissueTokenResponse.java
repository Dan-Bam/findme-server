package com.project.findme.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReissueTokenResponse {

    private String newAccessToken;
    private String newRefreshToken;

}
