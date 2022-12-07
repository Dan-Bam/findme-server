package com.project.findme.domain.user.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends FindmeException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
