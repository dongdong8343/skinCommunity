package com.project.skin.auth.jwt.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateAccessToken {
    @Getter
    @NoArgsConstructor
    public static class Response { // 응답이 나가는게 아니라서 api 밖으로 나갈 때 response라고 나간다. 다른 걸로 바꾸자.
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
