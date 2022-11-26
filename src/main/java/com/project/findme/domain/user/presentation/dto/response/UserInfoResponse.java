package com.project.findme.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    private String userName;

    private String address;

    private String phoneNumber;

    private String imageUrl;

}
