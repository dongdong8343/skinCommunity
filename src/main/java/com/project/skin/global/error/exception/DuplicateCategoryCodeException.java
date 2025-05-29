package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class DuplicateCategoryCodeException extends BusinessBaseException {
    public DuplicateCategoryCodeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateCategoryCodeException() {
        super(ErrorCode.DUPLICATE_CATEGORY_CODE);
    }
}