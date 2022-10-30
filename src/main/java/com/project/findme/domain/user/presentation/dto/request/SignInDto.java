package com.project.findme.domain.user.presentation.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    private String id;

    private String password;

}
