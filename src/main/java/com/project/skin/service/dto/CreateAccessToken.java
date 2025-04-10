package com.project.skin.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateAccessToken {
    @Getter
    @NoArgsConstructor
    public static class Request {
        private String refreshToken;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private String accessToken;
    }
}
