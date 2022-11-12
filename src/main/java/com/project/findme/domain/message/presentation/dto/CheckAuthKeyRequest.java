package com.project.findme.domain.message.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckAuthKeyRequest {

    @NotBlank(message = "핸드폰번호가 입력되지 않았습니다")
    private String phoneNumber;

//    @Length(min = 4, max = 4, message = "인증키는 4자리여야 합니다")
//    @NotNull(message = "인증키가 입력되지 않았습니다")
    private int authKey;

}
