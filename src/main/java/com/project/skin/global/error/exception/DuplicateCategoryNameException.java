package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class DuplicateCategoryNameException extends BusinessBaseException {
    public DuplicateCategoryNameException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicateCategoryNameException() {
        super(ErrorCode.DUPLICATE_CATEGORY_NAME);
    }
}

