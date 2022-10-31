package com.project.findme.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    // USER
    DUPLICATE_ID_EXCEPTION("중복된 이메일입니다.", 400),
    USER_NOT_FOUND("아이디를 찾을 수 없습니다.", 404),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),


    // TOKEN
    REFRESH_TOKEN_EXPIRED("refreshToken이 만료되었습니다.", 400),
    ;

    private final String msg;
    private final Integer status;
}
