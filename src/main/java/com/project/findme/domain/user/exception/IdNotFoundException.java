package com.project.findme.domain.user.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class IdNotFoundException extends FindmeException {

    public IdNotFoundException() {
        super(ErrorCode.ID_NOT_FOUND);
    }

}
