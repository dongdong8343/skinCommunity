package com.project.skin.category.service.dto;

import java.util.List;

import com.project.skin.category.entity.Category;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryList {

   @Getter
   public static class CategoryItem {

      private final Long id;
      private final Long parentId;
      private final String name;
      private final Boolean showFilter;

      private CategoryItem(Long id, Long parentId, String name, Boolean showFilter) {
         this.id = id;
         this.parentId = parentId;
         this.name = name;
         this.showFilter = showFilter;
      }

		public static CategoryItem from(Category category) {
			return new CategoryItem(category.getId(),
				Objects.isNull(category.getParent()) ? null : category.parentId(), category.getName(),
				category.getShowSkinFilter());
		}

		public static List<CategoryItem> from(List<Category> categories) {
			return categories.stream()
				.map(CategoryItem::from)
				.toList();
		}
   }

   @Getter
   @NoArgsConstructor
   public static class Response {

      List<CategoryItem> categoryItems;

      public Response(List<CategoryItem> categoryItems) {
         this.categoryItems = categoryItems;
      }
   }

   public static CategoryList.Response toResponse(List<Category> categories) {
      return new CategoryList.Response(CategoryItem.from(categories));
   }
}
