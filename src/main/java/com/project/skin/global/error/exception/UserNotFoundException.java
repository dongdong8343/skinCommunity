package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class UserNotFoundException extends BusinessBaseException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
