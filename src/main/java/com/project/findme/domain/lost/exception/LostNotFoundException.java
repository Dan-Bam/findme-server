package com.project.findme.domain.lost.exception;

import com.project.findme.global.error.exception.FindmeException;
import com.project.findme.global.error.type.ErrorCode;
import lombok.Getter;

@Getter
public class LostNotFoundException extends FindmeException {

    public LostNotFoundException() {
        super(ErrorCode.LOST_NOT_FOUND);
    }

}
