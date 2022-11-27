package com.project.findme.domain.chat.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendChatRequest {

    private Long roomId;

    private String message;

}
