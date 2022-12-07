package com.project.findme.domain.message.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class PhoneNumberNotFound extends FindmeException {

    public PhoneNumberNotFound() {
        super(ErrorCode.PHONE_NUMBER_NOT_FOUND);
    }

}
