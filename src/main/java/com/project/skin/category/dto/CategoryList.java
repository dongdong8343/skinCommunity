package com.project.skin.category.dto;

import java.util.List;

import com.project.skin.category.entity.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryList {
	@Getter
	public static class CategoryItem {
		private Long id;
		private Long parentId;
		private String name;
		private Boolean showFilter;

		private CategoryItem(Long id, Long parentId, String name, Boolean showFilter) {
			this.id = id;
			this.parentId = parentId;
			this.name = name;
			this.showFilter = showFilter;
		}

		public static CategoryItem from(Category category) {
			return new CategoryItem(category.getId(),
				category.getParent() == null ? null : category.getParent().getId(), category.getName(),
				category.getShowSkinFilter());
		}
	}

	@Getter
	@NoArgsConstructor
	public static class Response {
		List<CategoryItem> categoryItems;

		@Builder
		public Response(List<CategoryItem> categoryItems) {
			this.categoryItems = categoryItems;
		}
	}
}
