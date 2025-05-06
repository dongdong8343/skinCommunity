package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class InvalidRefreshToken extends BusinessBaseException {
    public InvalidRefreshToken(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidRefreshToken() {
        super(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
