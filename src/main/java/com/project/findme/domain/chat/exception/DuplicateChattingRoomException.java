package com.project.findme.domain.chat.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateChattingRoomException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicateChattingRoomException() {
        errorCode = ErrorCode.DUPLICATE_CHATTING_ROOM;
    }

}
