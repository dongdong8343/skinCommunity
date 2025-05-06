package com.project.skin.global.error.exception;

import com.project.skin.global.error.ErrorCode;

public class InvalidAccessToken extends BusinessBaseException {

  public InvalidAccessToken(ErrorCode errorCode) {
    super(errorCode);
  }

  public InvalidAccessToken() {
    super(ErrorCode.INVALID_ACCESS_TOKEN);
  }
}

