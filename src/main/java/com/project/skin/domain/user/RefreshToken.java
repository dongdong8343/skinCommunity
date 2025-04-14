package com.project.skin.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false, length = 1000)
    private String refreshToken;

    private RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public static RefreshToken makeRefreshToken(Long userId, String refreshToken) {
        return new RefreshToken(userId, refreshToken);
    }

    public void update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }


}
