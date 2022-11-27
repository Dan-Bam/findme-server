package com.project.findme.domain.user.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenExpiredException extends RuntimeException {

    private final ErrorCode errorCode;

    public RefreshTokenExpiredException() {
        this.errorCode = ErrorCode.REFRESH_TOKEN_EXPIRED;
    }
}
