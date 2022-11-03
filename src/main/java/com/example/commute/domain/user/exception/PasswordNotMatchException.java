package com.example.commute.domain.user.exception;

import com.example.commute.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordNotMatchException extends RuntimeException {
    private ErrorCode errorCode;

    public PasswordNotMatchException(String message) {
        super(message);
        this.errorCode = ErrorCode.PASSWORD_NOT_MATCH;
    }
}
