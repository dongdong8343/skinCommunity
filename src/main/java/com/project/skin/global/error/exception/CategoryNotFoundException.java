package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class CategoryNotFoundException extends BusinessBaseException {
	public CategoryNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

	public CategoryNotFoundException() {
		super(ErrorCode.CATEGORY_NOT_FOUND);
	}
}
