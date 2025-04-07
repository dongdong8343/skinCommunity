package com.project.skin.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // User 관련 에러 (U_)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U_001", "유저를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "U_002", "이미 사용 중인 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "U_003", "이미 사용 중인 닉네임입니다."),

    // Global 에러 (G_)
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "G_001", "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "G_002", "서버 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}