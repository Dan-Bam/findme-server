package com.project.findme.domain.user.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordNotMatchException extends FindmeException {

    public PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }

}
