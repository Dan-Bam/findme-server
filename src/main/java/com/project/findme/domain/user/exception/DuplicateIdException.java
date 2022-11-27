package com.project.findme.domain.user.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateIdException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicateIdException() {
        this.errorCode = ErrorCode.DUPLICATE_ID_EXCEPTION;
    }
}
