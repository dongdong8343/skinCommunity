package com.project.skin.service.token;

import com.project.skin.config.error.exception.UnauthorizedRefreshTokenMissing;
import com.project.skin.config.jwt.TokenProvider;
import com.project.skin.domain.user.User;
import com.project.skin.provider.token.RefreshTokenProvider;
import com.project.skin.provider.user.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final UserProvider userProvider;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
            throw new UnauthorizedRefreshTokenMissing();
        }

        Long userId = refreshTokenProvider.findByRefreshToken(refreshToken).getUserId();
        User user = userProvider.loadUserById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
