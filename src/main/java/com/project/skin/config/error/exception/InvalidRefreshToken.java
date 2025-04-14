package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class InvalidRefreshToken extends BusinessBaseException {
    public InvalidRefreshToken(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidRefreshToken() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
