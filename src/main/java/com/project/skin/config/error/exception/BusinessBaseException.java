package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class BusinessBaseException extends RuntimeException {
  private final ErrorCode errorCode;

  public BusinessBaseException(String message, ErrorCode errorcode) {
    super(message);
    this.errorCode = errorcode;
  }

  public BusinessBaseException(ErrorCode code) {
    super(code.getMessage());
    this.errorCode = code;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
