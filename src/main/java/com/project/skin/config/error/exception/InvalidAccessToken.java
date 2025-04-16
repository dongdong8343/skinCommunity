package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class InvalidAccessToken extends BusinessBaseException {

  public InvalidAccessToken(ErrorCode errorCode) {
    super(errorCode);
  }

  public InvalidAccessToken() {
    super(ErrorCode.INVALID_ACCESS_TOKEN);
  }
}

