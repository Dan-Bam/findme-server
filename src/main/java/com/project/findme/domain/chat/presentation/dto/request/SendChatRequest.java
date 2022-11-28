package com.project.findme.domain.chat.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class SendChatRequest {

    private Long roomId;

    private String message;

}
