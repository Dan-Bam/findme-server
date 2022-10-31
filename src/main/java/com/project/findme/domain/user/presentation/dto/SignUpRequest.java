package com.project.findme.domain.user.presentation.dto;

import com.project.findme.domain.user.entity.User;
import com.project.findme.domain.user.type.Role;
import lombok.*;

import java.util.Collections;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String id;

    private String password;

    private String userName;

    private String address;

    public User toEntity(String password) {
        return User.builder()
                .id(id)
                .password(password)
                .userName(userName)
                .address(address)
                .roles(Collections.singletonList(Role.ROLE_USER))
                .build();
    }

}
