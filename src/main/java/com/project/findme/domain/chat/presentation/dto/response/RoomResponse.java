package com.project.findme.domain.chat.presentation.dto.response;

import com.project.findme.domain.chat.domain.Room;
import com.project.findme.domain.chat.domain.RoomUser;
import com.project.findme.domain.user.entity.User;
import com.project.findme.global.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private Long roomId;
    private String roomName;
    private String roomImage;
    private LastChat lastChat;

    @Getter @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LastChat {
        private String lastMessage;
        private String lastSentAt;
    }

    public static RoomResponse of(RoomUser roomUser) {
        Room room = roomUser.getRoom();

        return RoomResponse
                .builder()
                .roomId(room.getId())
                .roomName(room.getRoomName())
                .roomImage(room.getImageUrl())
                .lastChat(LastChat
                        .builder()
                        .lastMessage(room.getLastChat().getLastMessage())
                        .lastSentAt(DateUtil.toTimeAgo(room.getLastChat().getLastSentAt()))
                        .build()
                )
                .build();
    }


}
