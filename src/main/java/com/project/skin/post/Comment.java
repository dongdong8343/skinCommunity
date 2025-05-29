package com.project.skin.post;

import com.project.skin.global.base.BaseTimeEntity;
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
    private Long id;

    private Long userId;

    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Comment> children = new ArrayList<>();

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer depth;

    @Column(nullable = false)
    private Integer commentOrder;

    private Comment(Long userId, Long postId, String content, Integer depth, Integer commentOrder) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.depth = depth;
        this.commentOrder = commentOrder;
    }

    private Comment(Long userId, Long postId, Comment parent, String content, Integer depth, Integer commentOrder) {
        this.userId = userId;
        this.postId = postId;
        this.parent = parent;
        this.content = content;
        this.depth = depth;
        this.commentOrder = commentOrder;
    }

    // 댓글 생성
    public static Comment createComment(Long userId, Long postId, String content, Integer depth, Integer commentOrder) {
        return new Comment(userId, postId, content, depth, commentOrder);
    }

    // 대댓글 생성
    public static Comment createSubComment(Long userId, Long postId, Comment parent, String content, Integer depth, Integer commentOrder) {
        return new Comment(userId, postId, parent, content, depth, commentOrder);
    }

    public void update(String content) {
        this.content = content;
    }
}
