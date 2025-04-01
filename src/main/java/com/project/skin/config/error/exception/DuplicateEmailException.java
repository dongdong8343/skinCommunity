package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class DuplicateEmailException extends BusinessBaseException {
    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_NICKNAME);
    }
}
