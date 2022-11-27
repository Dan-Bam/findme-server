package com.project.findme.global.error.response;

import com.project.findme.global.error.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMsg();
        this.status = errorCode.getStatus();
    }

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
