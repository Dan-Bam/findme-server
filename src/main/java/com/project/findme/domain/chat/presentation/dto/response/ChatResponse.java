package com.project.findme.domain.chat.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.findme.domain.chat.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.text.DateFormat;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ChatResponse {

    private Long roomId;

    @JsonProperty("isMine")
    private Boolean isMine;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a HH:mm")
    private LocalDateTime sentAt;

    public static ChatResponse of(Chat chat, Boolean isMine) {
        return ChatResponse.builder()
                .roomId(chat.getId())
                .isMine(isMine)
                .message(chat.getMessage())
                .sentAt(chat.getCreatedAt())
                .build();
    }

}
