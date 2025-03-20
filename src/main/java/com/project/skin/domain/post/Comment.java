package com.project.skin.domain.post;

import com.project.skin.domain.BaseTimeEntity;
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
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> child = new ArrayList<>();

    @Column(nullable = false)
    private String content;

    @Column(nullable = false) // 앞에 놈 알아야 할 듯
    private Integer depth;

    @Column(nullable = false) // 앞에 놈 알아야 할 듯
    private Integer commentOrder;

    private Comment(User user, Post post, String content, Integer depth, Integer commentOrder) {
        this.user = user;
        this.post = post;
        this.content = content;
        this.depth = depth;
        this.commentOrder = commentOrder;
    }

    private Comment(User user, Post post, Comment parent, String content, Integer depth, Integer commentOrder) {
        this.user = user;
        this.post = post;
        this.parent = parent;
        this.content = content;
        this.depth = depth;
        this.commentOrder = commentOrder;
    }

    // 댓글 생성
    public static Comment ofComment(User user, Post post, String content, Integer depth, Integer commentOrder) {
        return new Comment(user, post, content, depth, commentOrder);
    }

    // 대댓글 생성
    public static Comment ofSubComment(User user, Post post, Comment parent, String content, Integer depth, Integer commentOrder) {
        return new Comment(user, post, parent, content, depth, commentOrder);
    }

    public void update(String content) {
        this.content = content;
    }
}
