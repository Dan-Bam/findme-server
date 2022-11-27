package com.project.findme.domain.user.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidTokenException() {
        this.errorCode = ErrorCode.INVALID_TOKEN;
    }
}
