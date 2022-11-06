package com.project.findme.domain.user.exception;

import com.project.findme.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidTokenException(String message) {
        super(message);
        this.errorCode = ErrorCode.INVALID_TOKEN;
    }
}
