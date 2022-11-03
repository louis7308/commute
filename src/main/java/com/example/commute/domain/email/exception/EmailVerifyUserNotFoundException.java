package com.example.commute.domain.email.exception;

import com.example.commute.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class EmailVerifyUserNotFoundException extends RuntimeException {
    private ErrorCode errorCode;

    public EmailVerifyUserNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_VERIFY_USER_NOT_FOUND;
    }
}
