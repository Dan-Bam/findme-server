package com.project.findme.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoRequest {

    @NotBlank(message = "이름이 입력되지 않았습니다")
    private String userName;

    @NotBlank(message = "휴대폰 번호가 입력되지 않았습니다.")
    private String phoneNumber;

    @NotBlank(message = "주소가 입력되지 않았습니다")
    private String address;

}
