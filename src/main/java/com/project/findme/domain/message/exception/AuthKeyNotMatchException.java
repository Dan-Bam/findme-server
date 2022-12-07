package com.project.findme.domain.message.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class AuthKeyNotMatchException extends FindmeException {

    public AuthKeyNotMatchException() {
        super(ErrorCode.AUTH_KEY_NOT_CORRECT);
    }

}
