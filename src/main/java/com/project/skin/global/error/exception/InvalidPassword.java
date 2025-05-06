package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class InvalidPassword extends BusinessBaseException {

    public InvalidPassword(ErrorCode code) {
        super(code);
    }

    public InvalidPassword() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}