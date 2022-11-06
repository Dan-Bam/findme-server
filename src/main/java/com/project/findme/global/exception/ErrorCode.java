package com.project.findme.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    // USER
    DUPLICATE_ID_EXCEPTION("중복된 이메일입니다.", 409),
    ID_NOT_FOUND("아이디를 찾을 수 없습니다.", 404),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),


    // TOKEN
    REFRESH_TOKEN_EXPIRED("refreshToken이 만료되었습니다.", 403),
    INVALID_TOKEN("유효하지 않은 토근입니다.", 403),
    EXPIRATION_TOKEN("만료된 토큰입니다.", 403),


    // MESSAGE
    AUTH_KEY_NOT_CORRECT("인증키가 일치하지 않습니다.", 400),
    PHONE_NUMBER_NOT_FOUND("핸드폰 번호를 찾을 수 없습니다.", 404),


    // LOST
    LOST_NOT_FOUND("분실물 게시글을 찾을 수 없습니다.", 404),


    // SERVER
    INTERVAL_SERVER_ERROR("서버 오류 입니다 ^^ㅣ발", 500)
    ;

    private final String msg;
    private final Integer status;
}
