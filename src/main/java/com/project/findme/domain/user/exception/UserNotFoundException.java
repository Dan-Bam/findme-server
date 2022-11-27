package com.project.findme.domain.user.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserNotFoundException() {
        this.errorCode = ErrorCode.USER_NOT_FOUND;
    }
}
