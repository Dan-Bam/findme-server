package com.project.findme.domain.user.exception;

import com.project.findme.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateIdException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicateIdException(String message) {
        super(message);
        this.errorCode = ErrorCode.DUPLICATE_ID_EXCEPTION;
    }
}
