package com.project.skin.auth.jwt.service;

import com.project.skin.auth.jwt.provider.TokenProvider;
import com.project.skin.user.entity.User;
import com.project.skin.auth.jwt.provider.RefreshTokenProvider;
import com.project.skin.user.provider.UserProvider;
import com.project.skin.auth.jwt.dto.CreateAccessToken;
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
