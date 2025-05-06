package com.project.skin.auth.jwt.filter;

import com.project.skin.auth.jwt.dto.CreateAccessToken;
import com.project.skin.auth.jwt.provider.TokenProvider;
import com.project.skin.auth.jwt.service.RefreshTokenService;
import com.project.skin.auth.jwt.dto.TokenType;
import com.project.skin.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
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

    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("------------------------------------필터 동작합니다.");
        String accessToken = getToken(request, TokenType.ACCESS_TOKEN.getTokenType());

        if (tokenProvider.validToken(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            String refreshToken = getToken(request, TokenType.REFRESH_TOKEN.getTokenType());
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

    private String getToken(HttpServletRequest request, String tokenType) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String accessToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            accessToken = authorizationHeader.substring(TOKEN_PREFIX.length());
        } else {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(tokenType)) {
                        accessToken = cookie.getValue();
                        break;
                    }
                }
            }

        }

        return accessToken;
    }
}