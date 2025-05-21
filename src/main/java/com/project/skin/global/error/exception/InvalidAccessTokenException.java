package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class InvalidAccessTokenException extends BusinessBaseException {

  public InvalidAccessTokenException(ErrorCode errorCode) {
    super(errorCode);
  }

  public InvalidAccessTokenException() {
    super(ErrorCode.INVALID_ACCESS_TOKEN);
  }
}

