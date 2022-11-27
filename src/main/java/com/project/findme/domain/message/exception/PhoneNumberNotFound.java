package com.project.findme.domain.message.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class PhoneNumberNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public PhoneNumberNotFound(String message) {
        super(message);
        this.errorCode = ErrorCode.PHONE_NUMBER_NOT_FOUND;
    }
}
