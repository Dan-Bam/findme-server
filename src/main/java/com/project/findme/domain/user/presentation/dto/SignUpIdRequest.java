package com.project.findme.domain.user.presentation.dto;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.type.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpIdRequest {

    @NotBlank(message = "아이디가 입력되지 않았습니다")
    private String id;

    @NotBlank(message = "비밀번호가 입력되지 않았습니다")
    private String password;

    public User toEntity(String password) {
        return User.builder()
                .id(id)
                .password(password)
                .roles(Collections.singletonList(Role.ROLE_USER))
                .build();
    }

}
