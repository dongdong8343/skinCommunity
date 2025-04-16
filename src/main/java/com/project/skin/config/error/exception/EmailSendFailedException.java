package com.project.skin.config.error.exception;

import com.project.skin.config.error.ErrorCode;

public class EmailSendFailedException extends BusinessBaseException {
    public EmailSendFailedException(ErrorCode code) {
        super(code);
    }

    public EmailSendFailedException() {
        super(ErrorCode.EMAIL_SEND_FAILED);
    }
}
