package com.project.findme.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "아이디가 입력되지 않았습니다")
    private String id;

    @Pattern(regexp = "[0-9a-zA-Z]{0,20}", message = "영어와 숫자가 한글자라도 들어가야 합니다")
    @Size(min = 5, max = 20, message = "비밀번호는 5~20자리여야 합니다")
    @NotBlank(message = "비밀번호가 입력되지 않았습니다")
    private String password;

}
