package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class UnauthorizedRefreshTokenMissing extends BusinessBaseException {
    public UnauthorizedRefreshTokenMissing(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnauthorizedRefreshTokenMissing() {
        super(ErrorCode.DUPLICATE_NICKNAME);
    }
}
