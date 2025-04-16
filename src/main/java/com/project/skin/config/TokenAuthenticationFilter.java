package com.project.skin.config;

import com.project.skin.config.jwt.TokenProvider;
import com.project.skin.config.jwt.TokenType;
import com.project.skin.service.dto.CreateAccessToken;
import com.project.skin.service.jwt.RefreshTokenService;
import com.project.skin.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final CookieUtil cookieUtil;

//    private final static String HEADER_AUTHORIZATION = "Authorization";
//    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        String accessToken = getToken(request.getHeader(HEADER_AUTHORIZATION));
//
//        if (accessToken == null) {
//            accessToken = getToken(request.getCookies(), "accessToken");
//        }

        String accessToken = getToken(request.getCookies(), TokenType.ACCESS_TOKEN.getTokenType());

        if (tokenProvider.validToken(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            String refreshToken = getToken(request.getCookies(), TokenType.REFRESH_TOKEN.getTokenType());
            if (tokenProvider.validToken(refreshToken)) {
                CreateAccessToken.Response createAccessToken = refreshTokenService.createNewAccessToken(refreshToken);
                accessToken = createAccessToken.getAccessToken();

                response.addCookie(cookieUtil.createCookie(TokenType.ACCESS_TOKEN.getTokenType(), createAccessToken.getAccessToken(), createAccessToken.getAccessTokenCookieMaxAge()));

                Authentication authentication = tokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(Cookie[] cookies, String tokenType) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(tokenType)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

//    private String getToken(String authorizationHeader) {
//        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
//            return authorizationHeader.substring(TOKEN_PREFIX.length());
//        }
//        return null;
//    }

}