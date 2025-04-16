package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class UserNotFoundException extends BusinessBaseException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
