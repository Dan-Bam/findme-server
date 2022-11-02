package com.project.findme.domain.message.presentation.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberRequest {

    @NotBlank(message = "핸드폰번호가 입력되지 않았습니다")
    private String phoneNumber;

}
