package com.project.skin.domain.post;

import com.project.skin.domain.BaseTimeEntity;
import com.project.skin.domain.category.Category;
import com.project.skin.domain.user.User;
import jakarta.persistence.*;
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
    private Long postId;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private Integer likeCount;

    private Post(User user, Category category, String title, String content) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.likeCount = 0;
    }

    public static Post of(User user, Category category, String title, String content) {
        return new Post(user, category, title, content);
    }

    public void updatePost(Category category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public void updateLikeCount(int likeChange) {
        this.likeCount += likeChange;
    }

}
