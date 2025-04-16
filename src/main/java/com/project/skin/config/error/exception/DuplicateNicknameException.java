package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class DuplicateNicknameException extends BusinessBaseException {
    public DuplicateNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateNicknameException() {
        super(ErrorCode.DUPLICATE_NICKNAME);
    }
}
