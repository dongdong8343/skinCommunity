package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class DuplicateNicknameException extends BusinessBaseException {
    public DuplicateNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateNicknameException() {
        super(ErrorCode.DUPLICATE_NICKNAME);
    }
}
