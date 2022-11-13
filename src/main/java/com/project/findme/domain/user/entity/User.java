package com.project.findme.domain.user.entity;

import com.project.findme.domain.user.type.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String id;

    private String password;

    private String phoneNumber;

    private String address;

    private String userName;

    private String refreshToken;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "userID"))
    private List<Role> roles = new ArrayList<>();

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    public void updateAddress(String address) {
        this.address = address;
    }
}
