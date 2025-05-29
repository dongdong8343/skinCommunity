package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class InvalidRefreshTokenException extends BusinessBaseException {
    public InvalidRefreshTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
