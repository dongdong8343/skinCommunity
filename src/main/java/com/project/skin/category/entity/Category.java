package com.project.skin.category.entity;

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

    @Column(nullable = false)
    private Long categoryOrder;

    @Column(nullable = false)
    private Boolean showSkinFilter;

    private Category(String code, String name, Long categoryOrder, Boolean showSkinFilter) {
        this.code = code;
        this.name = name;
        this.categoryOrder = categoryOrder;
        this.showSkinFilter = showSkinFilter;
    }

    public static Category createCategory(String code, String name, Long order, Boolean showSkinFilter) {
        return new Category(code, name, order, showSkinFilter);
    }

    public void addSubCategory(Category child) {
        if (this.children.contains(child)) {
            return;
        }

        this.children.add(child);
        child.addParent(this);
    }

    public void addParent(Category parent) {
        this.parent = parent;
    }
}
