package com.project.skin.domain.post;

import com.project.skin.domain.BaseTimeEntity;
import com.project.skin.domain.category.Category;
import com.project.skin.domain.user.User;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long categoryId;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private Long likeCount = 0L;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<>();


    private Post(Long userId, Long categoryId, String title, String content, List<File> files) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.files = files;
    }

    public static Post create(Long userId, Long categoryId, String title, String content, List<File> files) {
        return new Post(userId, categoryId, title, content, files);
    }

    public boolean isMine(User user) {
        return Objects.equals(this.userId, user.getId());
    }
}
