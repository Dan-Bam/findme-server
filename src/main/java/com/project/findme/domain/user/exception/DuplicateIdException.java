package com.project.findme.domain.user.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateIdException extends FindmeException {

    public DuplicateIdException() {
        super(ErrorCode.DUPLICATE_ID_EXCEPTION);
    }

}
