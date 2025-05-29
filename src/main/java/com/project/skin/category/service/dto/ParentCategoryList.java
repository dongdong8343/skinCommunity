package com.project.skin.category.service.dto;

import java.util.List;

import com.project.skin.category.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ParentCategoryList {
	@Getter
	public static class CategoryItem {
		private final Long id;
		private final String name;
		private final Long order;

		private CategoryItem(Long id, String name, Long order) {
			this.id = id;
			this.name = name;
			this.order = order;
		}

		public static ParentCategoryList.CategoryItem from(Category category) {
			return new ParentCategoryList.CategoryItem(category.getId(), category.getName(),
				category.getCategoryOrder());
		}

		public static List<ParentCategoryList.CategoryItem> from(List<Category> categories) {
			return categories.stream().map(ParentCategoryList.CategoryItem::from).toList();
		}
	}

	@Getter
	@NoArgsConstructor
	public static class Response {
		List<ParentCategoryList.CategoryItem> categoryItems;

		public Response(List<ParentCategoryList.CategoryItem> categoryItems) {
			this.categoryItems = categoryItems;
		}
	}

	public static ParentCategoryList.Response toCategoryList(List<Category> categories) {
		return new ParentCategoryList.Response(ParentCategoryList.CategoryItem.from(categories));
	}
}
