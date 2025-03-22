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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> children = new ArrayList<>();

    @Column(length = 20, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private Category(Category parent, String code, String name) {
        this(code, name);
        this.parent = parent;
    }

    // 카테고리 생성
    public Category createCategory(String code, String name) {
        return new Category(code, name);
    }

    // 서브 카테고리 생성
    public Category createSubCategory(Category parent, String code, String name) {
        return new Category(parent, code, name);
    }
}
