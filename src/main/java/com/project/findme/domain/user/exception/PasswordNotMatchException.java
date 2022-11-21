package com.project.findme.domain.user.exception;

import com.project.findme.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordNotMatchException extends RuntimeException {

    private final ErrorCode errorCode;

    public PasswordNotMatchException() {
        this.errorCode = ErrorCode.PASSWORD_NOT_MATCH;
    }
}
