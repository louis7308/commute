package com.example.commute.domain.user.exception;

import com.example.commute.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserExistException(String message) {
        super(message);
        this.errorCode = ErrorCode.USER_EXIST;
    }

}
