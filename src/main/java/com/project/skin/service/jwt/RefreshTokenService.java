package com.project.skin.service.jwt;

import com.project.skin.config.jwt.TokenProvider;
import com.project.skin.domain.user.User;
import com.project.skin.provider.jwt.RefreshTokenProvider;
import com.project.skin.provider.user.UserProvider;
import com.project.skin.service.dto.CreateAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final UserProvider userProvider;

    @Transactional(readOnly = true)
    public CreateAccessToken.Response createNewAccessToken(String refreshToken) {
        Long userId = refreshTokenProvider.findByRefreshToken(refreshToken).getUserId();
        User user = userProvider.loadUserById(userId);

        return tokenProvider.generateAccessToken(user.getEmail(), user.getUserRoles().stream().map(userRole -> userRole.getRole().getKey()).toList());
    }
}
