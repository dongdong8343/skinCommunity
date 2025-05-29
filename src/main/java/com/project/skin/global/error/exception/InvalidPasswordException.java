package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class InvalidPasswordException extends BusinessBaseException {

    public InvalidPasswordException(ErrorCode code) {
        super(code);
    }

    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}