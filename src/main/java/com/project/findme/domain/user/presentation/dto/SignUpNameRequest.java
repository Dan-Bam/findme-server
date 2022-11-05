package com.project.findme.domain.user.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpNameRequest {

    @NotBlank(message = "아이디가 입력되지 않았습니다")
    private String id;

    @NotBlank(message = "이름이 입력되지 않았습니다")
    private String userName;

}
