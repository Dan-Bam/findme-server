package com.project.findme.domain.lost.exception;

import com.project.findme.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class LostNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public LostNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.LOST_NOT_FOUND;
    }
}
