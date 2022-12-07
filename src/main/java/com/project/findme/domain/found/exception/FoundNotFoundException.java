package com.project.findme.domain.found.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class FoundNotFoundException extends FindmeException {

    public FoundNotFoundException() {
        super(ErrorCode.FOUND_NOT_FOUND);
    }

}
