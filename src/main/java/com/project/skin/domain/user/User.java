package com.project.skin.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String type;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 100, nullable = true)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDateTime deletedAt;

    private User(String type, String email, String password, String name, String nickname) {
        this.type = type;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static User ofUser(String type, String email, String password, String name, String nickname) {
        return new User(type, email, password, name, nickname);
    }

    // 아래 메서드처럼 구체적으로 구현하는게 좋을까? 아니면 오버라이딩을 통해 메서드 명을 통일하는 것이 좋을까?
    public User updatePassword(String password) {
        this.password = password;
        this.updatedAt = LocalDateTime.now();

        return this;
    }

    public User updateNickname(String nickname) {
        this.nickname = nickname;
        this.updatedAt = LocalDateTime.now();

        return this;
    }

    public User updateUser(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;
        this.updatedAt = LocalDateTime.now();

        return this;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
