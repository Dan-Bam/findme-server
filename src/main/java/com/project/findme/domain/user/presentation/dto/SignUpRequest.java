package com.project.findme.domain.user.presentation.dto;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.type.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "아이디가 입력되지 않았습니다")
    private String id;

    @Pattern(regexp = "[0-9a-zA-Z]{0,20}", message = "영어와 숫자가 한글자라도 들어가야 합니다")
    @Size(min = 5, max = 20, message = "비밀번호는 5~20자리여야 합니다")
    @NotBlank(message = "비밀번호가 입력되지 않았습니다")
    private String password;

    @NotBlank(message = "휴대폰 번호가 입력되지 않았습니다.")
    private String phoneNumber;

    @NotBlank(message = "이름이 입력되지 않았습니다")
    private String userName;

    @NotBlank(message = "주소가 입력되지 않았습니다")
    private String address;

    public User toEntity(String password) {
        return User.builder()
                .id(id)
                .password(password)
                .phoneNumber(phoneNumber)
                .userName(userName)
                .address(address)
                .roles(Collections.singletonList(Role.ROLE_USER))
                .build();
    }

}
