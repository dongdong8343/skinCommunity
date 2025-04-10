package com.project.skin.config.jwt;

import com.project.skin.domain.user.User;
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

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // 토큰 생성
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        List<String> roles = user.getUserRoles().stream().map(role -> role.getRole().getKey()).toList();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 타입 - JWT
                .setIssuer(jwtProperties.getIssuer()) // 내용 iss(발급자) : properties 파일에서 설정한 값
                .setIssuedAt(now) // 내용 iat(발급일시) : 현재 시간
                .setExpiration(expiry) // 내용 exp(만료일시) : expiry 멤버 변숫값
                .setSubject(user.getEmail()) // 내용 sub(토큰 제목) : 유저의 이메일
                .claim("email", user.getEmail()) // 클레임 email : 유저 email
                .claim("roles", roles) // 클레임 roles : 유저 권한들
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 서명 - 비밀 값과 함께 암호화
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

    // 토큰 기반으로 유저 email 가져오는 메서드
    public String getUserEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get("email", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
