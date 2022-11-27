package com.project.findme.domain.chat.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class ChattingRoomNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public ChattingRoomNotFoundException() {
        this.errorCode = ErrorCode.CHATTING_ROOM_NOT_FOUND;
    }

}
