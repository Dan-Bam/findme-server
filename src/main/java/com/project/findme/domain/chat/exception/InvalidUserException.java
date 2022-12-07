package com.project.findme.domain.chat.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidUserException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidUserException() {
        this.errorCode = ErrorCode.INVALID_USER;
    }

}
