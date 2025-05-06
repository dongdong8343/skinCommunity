package com.project.skin.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class Login {
    @Getter
    @NoArgsConstructor
    public static class Request{
        private String email;
        private String password;
    }

    @Getter
    @NoArgsConstructor
    public static class Response {
        private String accessToken;
        private String refreshToken;
        private int accessTokenCookieMaxAge;
        private int refreshTokenCookieMaxAge;

        private Response(String accessToken, String refreshToken, int accessTokenCookieMaxAge, int refreshTokenCookieMaxAge) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.accessTokenCookieMaxAge = accessTokenCookieMaxAge;
            this.refreshTokenCookieMaxAge = refreshTokenCookieMaxAge;
        }

        public static Response create(String accessToken, String refreshToken, int accessTokenCookieMaxAge, int refreshTokenCookieMaxAge) {
            return new Response(accessToken, refreshToken, accessTokenCookieMaxAge, refreshTokenCookieMaxAge);
        }
    }
}
