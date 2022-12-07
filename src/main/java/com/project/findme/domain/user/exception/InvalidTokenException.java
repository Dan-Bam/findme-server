package com.project.findme.domain.user.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends FindmeException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

}
