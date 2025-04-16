package com.project.skin.domain.post;

import com.project.skin.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private Long likeCount = 0L;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private List<File> files;

    private Post(String title, String content, Long viewCount, Long likeCount, Long userId, Long categoryId, List<Comment> comments, List<File> files) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.userId = userId;
        this.categoryId = categoryId;
        this.comments = comments;
        this.files = files;
    }

    public static Post create(String title, String content, Long viewCount, Long likeCount, Long userId, Long categoryId, List<Comment> comments, List<File> files) {
        return new Post(title, content, viewCount, likeCount, userId, categoryId, comments, files);
    }

    public void updatePost(String title, String content, Long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }
}
