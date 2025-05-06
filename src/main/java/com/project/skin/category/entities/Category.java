package com.project.skin.category.entities;

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
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    // 카테고리 생성 -> 하나로 합치는 구조
    public static Category createCategory(String code, String name) {
        return new Category(code, name);
    }

    // 서브 카테고리 생성
    public static Category createSubCategory(Category parent, String code, String name) {
        Category subCategory = new Category(parent, code, name);
        parent.getChildren().add(subCategory);

        return subCategory;
    }

    public void addChildrenCategory(Category subCategory) {
        this.getChildren().add(subCategory);
    }
}
