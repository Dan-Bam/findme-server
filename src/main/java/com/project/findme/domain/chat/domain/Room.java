package com.project.findme.domain.chat.domain;

import com.project.findme.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @OneToMany
    private List<RoomUser> roomUsers;

    @Embedded
    private LastChat lastChat;

    private String imageUrl;

    @Getter @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Embeddable
    public static class LastChat {
        private String lastMessage;
        private LocalDateTime lastSentAt;
    }

    public void updateLastMessage(Chat chat) {
        this.lastChat.lastMessage = chat.getMessage();
        this.lastChat.lastSentAt = chat.getCreatedAt();
    }

    public void addRoomUser(User user) {
        this.roomUsers.add(RoomUser
                .builder()
                .room(this)
                .user(user)
                .build());
    }

}
