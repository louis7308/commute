package com.example.commute.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    AUTH_CODE_MISMATCH("인증번호가 일치하지 않습니다,",400),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),
    TOKEN_EXPIRATION("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),

    USER_NOT_FOUND("유저를 찾을수 없습니다.", 404),
    EMAIL_VERIFY_USER_NOT_FOUND("이메일 인증 된 유저를 찾을 수 없습니다.", 404),
    USER_EXIST("유저가 존재합니다", 409),
    EMAIL_ALREADY_EXIST("존재하는 이메일 입니다.", 409),
    MANY_REQUEST_EMAIL_AUTH("15분에 최대 3번 이메일 인증을 요청할 수 있습니다.", 429),
    EMAIL_SEND_FAIL("메일 발송에 실패 했습니다",500);

    private final String message;
    private final int status;
}
