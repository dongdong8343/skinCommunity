package com.project.skin.category.dto;

import com.project.skin.category.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class AddCategory {
    @ToString
    @Getter
    @NoArgsConstructor
    public static class Request {
        private String name;
        private String code;
        private Long parentId;
        private Long order; // 없다면 0을 넘겨준다.
        private Boolean showSkinFilter;

        public Category toEntity() {
            return Category.createCategory(this.code, this.name, this.order + 1, this.showSkinFilter);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long categoryId;

        @Builder
        public Response(Long categoryId) {
            this.categoryId = categoryId;
        }
    }
}
