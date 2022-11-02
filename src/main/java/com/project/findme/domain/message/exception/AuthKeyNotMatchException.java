package com.project.findme.domain.message.exception;

import com.project.findme.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthKeyNotMatchException extends RuntimeException {

    private final ErrorCode errorCode;

    public AuthKeyNotMatchException(String message) {
        super(message);
        this.errorCode = ErrorCode.AUTH_KEY_NOT_CORRECT;
    }
}
