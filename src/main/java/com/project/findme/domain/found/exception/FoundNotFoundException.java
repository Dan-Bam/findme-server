package com.project.findme.domain.found.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class FoundNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public FoundNotFoundException() {
        this.errorCode = ErrorCode.FOUND_NOT_FOUND;
    }

}
