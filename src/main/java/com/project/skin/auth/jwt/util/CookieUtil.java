package com.project.skin.auth.jwt.util;


import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public Cookie createCookie(String cookieName, String cookieValue, int cookieMaxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(cookieMaxAge);

        return cookie;
    }
}
