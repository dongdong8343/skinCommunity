package com.project.skin.provider.jwt;

import com.project.skin.config.error.exception.InvalidRefreshToken;
import com.project.skin.domain.user.RefreshToken;
import com.project.skin.repository.jwt.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class RefreshTokenProvider {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByUserId(Long userId) {
        return refreshTokenRepository.findByUserId(userId).orElse(null);
    }

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidRefreshToken::new);
    }

    public void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }
}
