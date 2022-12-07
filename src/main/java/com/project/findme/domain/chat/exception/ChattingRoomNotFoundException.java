package com.project.findme.domain.chat.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class ChattingRoomNotFoundException extends FindmeException {

    public ChattingRoomNotFoundException() {
        super(ErrorCode.CHATTING_ROOM_NOT_FOUND);
    }

}
