package com.project.skin.provider.token;

import com.project.skin.config.error.exception.UnauthorizedRefreshTokenMissing;
import com.project.skin.domain.user.RefreshToken;
import com.project.skin.repository.token.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenProvider {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(UnauthorizedRefreshTokenMissing::new);
    }
}
