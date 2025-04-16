package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class InvalidPassword extends BusinessBaseException {

    public InvalidPassword(ErrorCode code) {
        super(code);
    }

    public InvalidPassword() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}