package com.project.findme.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {

    private Long roomId;
    private String roomName;
    private String roomLogoImage;
    private LastChat lastChat;

    public static class LastChat {
        private String lastMessage;
        private String lastSentAt;
    }

}
