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

    private String imageUrl;

    private String refreshToken;

    @Builder.Default
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "userID"))
    private List<Role> roles = new ArrayList<>();

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUserInfo(String userName, String address, String phoneNumber, String imageUrl) {
        this.userName = userName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageUrl = "https://kdn-findme-bucket.s3.ap-northeast-2.amazonaws.com/USER/" + imageUrl;
    }

}
