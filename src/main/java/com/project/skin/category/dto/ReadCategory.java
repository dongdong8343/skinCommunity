package com.project.skin.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class ReadCategory {
    @ToString
    @Getter
    public static class CategoryDetail {
        private Long id;
        private Long parentId;
        private String code;
        private String name;
        private Long categoryOrder;
        private Boolean showSkinFilter;
        private List<CategoryDetail> children;

        @Builder
        public CategoryDetail(Long id, Long parentId, List<CategoryDetail> children, String code, String name, Long categoryOrder, Boolean showSkinFilter) {
            this.id = id;
            this.parentId = parentId;
            this.children = new ArrayList<>();
            this.code = code;
            this.name = name;
            this.categoryOrder = categoryOrder;
            this.showSkinFilter = showSkinFilter;
        }
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class Response {
        private final List<CategoryDetail> categoryTree = new ArrayList<>();
    }
}
