package com.project.findme.global.error.exception;

import com.project.findme.global.error.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindmeException extends RuntimeException {

    final ErrorCode errorCode;

}
