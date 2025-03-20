package com.project.skin.domain.category;

import com.project.skin.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Category> child = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private Category(Category parent, String code, String name) {
        this.parent = parent;
        this.code = code;
        this.name = name;
    }

    private Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // 카테고리 생성
    public Category ofCategory(String code, String name) {
        return new Category(code, name);
    }

    // 서브 카테고리 생성
    public Category ofSubCategory(Category parent, String code, String name) {
        return new Category(parent, code, name);
    }
}
