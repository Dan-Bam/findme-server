package com.project.findme.domain.chat.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidUserException extends FindmeException {

    public InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }

}
