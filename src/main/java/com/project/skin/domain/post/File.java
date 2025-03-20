package com.project.skin.domain.post;

import com.project.skin.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class File extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String url;

    @Column(length = 500, nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String bucketName;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String fileType;

    private File(Post post, String fileName, String originalName, String url, String filePath, String bucketName, Long fileSize, String fileType) {
        this.post = post;
        this.fileName = fileName;
        this.originalName = originalName;
        this.url = url;
        this.filePath = filePath;
        this.bucketName = bucketName;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public static File of(Post post, String fileName, String orgName, String url, String filePath, String bucketName, Long fileSize, String fileType) {
        return new File(post, fileName, orgName, url, filePath, bucketName, fileSize, fileType);
    }
}
