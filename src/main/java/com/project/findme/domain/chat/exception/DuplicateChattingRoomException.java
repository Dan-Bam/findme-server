package com.project.findme.domain.chat.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateChattingRoomException extends FindmeException {

    public DuplicateChattingRoomException() {
        super(ErrorCode.DUPLICATE_CHATTING_ROOM);
    }

}
