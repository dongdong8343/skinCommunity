package com.project.skin.domain.user;

import com.project.skin.domain.BaseTimeEntity;
import com.project.skin.domain.post.Comment;
import com.project.skin.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Column(length = 30, nullable = false)
    private String type;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime deletedAt;

    private User(String type, String email, String password, String name, String nickname) {
        this.type = type;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }

    public static User ofUser(String type, String email, String password, String name, String nickname) {
        return new User(type, email, password, name, nickname);
    }

    // 아래 메서드처럼 구체적으로 구현하는게 좋을까요? 아니면 오버라이딩을 통해 메서드 명을 통일하는 것이 좋을까요?
    public User updatePassword(String password) {
        this.password = password;

        return this;
    }

    public User updateNickname(String nickname) {
        this.nickname = nickname;

        return this;
    }

    public User updateUser(String password, String nickname) {
        this.password = password;
        this.nickname = nickname;

        return this;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
