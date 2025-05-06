package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class DuplicateEmailException extends BusinessBaseException {
    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
