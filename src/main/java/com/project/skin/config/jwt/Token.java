package com.project.skin.config.jwt;

import com.project.skin.service.dto.Login;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Token {
    private String accessToken;
    private String refreshToken;
    private int accessTokenCookieMaxAge;
    private int refreshTokenCookieMaxAge;

    private Token(String accessToken, String refreshToken, int accessTokenCookieMaxAge, int refreshTokenCookieMaxAge) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenCookieMaxAge = accessTokenCookieMaxAge;
        this.refreshTokenCookieMaxAge = refreshTokenCookieMaxAge;
    }

    public static Token create(String accessToken, String refreshToken, int accessTokenCookieMaxAge, int refreshTokenCookieMaxAge) {
        return new Token(accessToken, refreshToken, accessTokenCookieMaxAge, refreshTokenCookieMaxAge);
    }
}
