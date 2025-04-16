package com.project.skin.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateAccessToken {
    @Getter
    @NoArgsConstructor
    public static class Response {
        private String accessToken;
        private int accessTokenCookieMaxAge;

        private Response(String accessToken, int accessTokenCookieMaxAge) {
            this.accessToken = accessToken;
            this.accessTokenCookieMaxAge = accessTokenCookieMaxAge;
        }

        public static Response create(String accessToken, int accessTokenCookieMaxAge) {
            return new Response(accessToken, accessTokenCookieMaxAge);
        }
    }
}
