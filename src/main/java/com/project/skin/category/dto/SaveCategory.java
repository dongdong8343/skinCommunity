package com.project.skin.category.dto;

import com.project.skin.category.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class SaveCategory {
    @ToString
    @Getter
    @NoArgsConstructor
    public static class Request {
        private String code;
        private String name;
        private Long order;
        private Boolean showSkinFilter;
        private List<Request> children;

        public Category toEntity() {
            return Category.createCategory(this.code, this.name, this.order, this.showSkinFilter);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {
        private final List<Long> categoryIdList = new ArrayList<>();

        public void addCategoryId(Long categoryId) {
            categoryIdList.add(categoryId);
        }
    }

    public static Category buildCategory(Request parent, List<Request> children) {
        Category category = parent.toEntity();
        for (Request child : children) {
            category.addSubCategory(buildCategory(child, child.getChildren()));
        }

        return category;
    }
}
