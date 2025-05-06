package com.project.skin.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddCategory {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String categoryName;
        private String categoryCode;
        private String subCategoryName;
        private String subCategoryCode;
    }

    @Getter
    public static class Response {
        private Long categoryId;
        private Long subCategoryId;

        private Response(Long categoryId, Long subCategoryId) {
            this.categoryId = categoryId;
            this.subCategoryId = subCategoryId;
        }

        public static Response createResponse(Long categoryId, Long subCategoryId) {
            return new Response(categoryId, subCategoryId);
        }
    }
}
