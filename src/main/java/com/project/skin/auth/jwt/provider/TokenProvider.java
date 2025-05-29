package com.project.skin.auth.jwt.provider;

import com.project.skin.auth.jwt.service.dto.CreateAccessToken;
import com.project.skin.auth.jwt.config.JwtProperties;
import com.project.skin.auth.jwt.service.dto.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    private static final Duration ACCESS_TOKEN_EXPIRY = Duration.ofMinutes(15);
    private static final Duration REFRESH_TOKEN_EXPIRY = Duration.ofDays(7);

    public CreateAccessToken.Response generateAccessToken(String email, List<String> roles) {
        return CreateAccessToken.Response.create(makeAccessToken(email, roles), (int)ACCESS_TOKEN_EXPIRY.getSeconds());
    }

    public Token generateTokens(Long userId, String email, List<String> roles){
        String accessToken = makeAccessToken(email, roles);
        String refreshToken = makeRefreshToken(userId);

        return Token.create(accessToken, refreshToken, (int)ACCESS_TOKEN_EXPIRY.getSeconds(), (int)REFRESH_TOKEN_EXPIRY.getSeconds());
    }

    private String makeAccessToken(String email, List<String> roles) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입 - JWT
                .setIssuer(jwtProperties.getIssuer()) // 내용 iss(발급자) : properties 파일에서 설정한 값
                .setIssuedAt(now) // 내용 iat(발급일시) : 현재 시간
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRY.toMillis())) // 내용 exp(만료일시)
                .setSubject(email) // 내용 sub(토큰 제목) : 유저의 이메일
                .claim("email", email) // 클레임 email : 유저 email
                .claim("roles", roles) // 클레임 roles : 유저 권한들
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 서명 - 비밀 값과 함께 암호화
                .compact();
    }

    private String makeRefreshToken(Long userId) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRY.toMillis()))
                .setSubject(String.valueOf(userId))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // 토큰 유효성 검증
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀 값으로 복호화
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰 기반으로 인증 정보 가져오는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) claims.get("roles");

        Set<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User
                (claims.getSubject(), "", authorities), token, authorities);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
