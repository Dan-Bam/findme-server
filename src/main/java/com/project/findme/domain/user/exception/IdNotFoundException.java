package com.project.findme.domain.user.exception;

import com.project.findme.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class IdNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public IdNotFoundException() {
        this.errorCode = ErrorCode.ID_NOT_FOUND;
    }
}
