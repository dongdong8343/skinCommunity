package com.project.skin.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.project.skin.category.dto.AddCategory;
import com.project.skin.category.dto.CategoryList;
import com.project.skin.category.dto.ReOrderCategory;
import com.project.skin.category.dto.UpdateCategory;
import com.project.skin.category.entity.Category;
import com.project.skin.category.provider.CategoryProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class CategoryService {
	private final CategoryProvider categoryProvider;

	@Transactional(readOnly = true)
	public CategoryList.Response getCategories(Long parentId) {
		// + deletedAt이 null인 카테고리만 찾기
		// parentId가 null이면 parentId가 null인 카테고리 찾기

		// 그게 아니라면 parentId에 해당하는 카테고리 찾기
		List<Category> categories = categoryProvider.getCategoriesByParentId(parentId);

		return CategoryList.Response.builder()
			.categoryItems(categories.stream().map(CategoryList.CategoryItem::from).toList())
			.build();
	}

	@Transactional
	public AddCategory.Response saveCategory(AddCategory.Request request) {
		// 카테고리 코드 중복 검사
		categoryProvider.findCategoryByCode(request.getCode());

		// 부모 카테고리 찾기
		Category parentCategory = categoryProvider.findCategoryByIdOrNull(request.getParentId());
		Category childCategory = null;

		if (Objects.isNull(parentCategory)) { // 부모 카테고리 없는 경우 부모 카테고리 생성
			parentCategory = request.toEntity();
		} else { // 부모 카테고리 있는 경우 자식 카테고리 생성
			childCategory = request.toEntity();
			parentCategory.addSubCategory(childCategory);
		}

		categoryProvider.saveCategory(parentCategory);

		Long categoryId = Objects.isNull(childCategory) ? parentCategory.getId() : childCategory.getId();

		// 새로 만들어진 카테고리의 아이디 반환 -> 자식 카테고리를 저장하는 경우 db에는 저장이 되는데 id가 null로 반환됨...
		return AddCategory.Response.builder()
			.categoryId(categoryId)
			.build();
	}

	@Transactional
	public UpdateCategory.Response updateCategory(UpdateCategory.Request request) {
		categoryProvider.findCategoryByCode(request.getCode());

		Category category = categoryProvider.findCategoryByIdOrThrow(request.getId());

		// parentId로 newParent 찾기
		Category newParent = null;
		if(Objects.nonNull(request.getParentId())) {
			newParent = categoryProvider.findCategoryByIdOrThrow(request.getParentId());
		}

		// category.update(속성들 넘겨주기)
		category.update(request.getName(), request.getCode(), request.getShowSkinFilter());

		// parentId가 null이 아니고 기존 부모 id와 다른 경우 부모 - 자식 관계 수정
		if (Objects.nonNull(newParent) && !Objects.equals(newParent.getId(), category.getParent().getId())) {
			Category oldParent = category.getParent();

			oldParent.getChildren().removeIf(child -> Objects.equals(child.getId(), request.getId()));

			newParent.addSubCategory(category);
		}

		return UpdateCategory.Response.builder()
			.id(category.getId())
			.build();
	}

	@Transactional
	public ReOrderCategory.Response reOrderCategories(ReOrderCategory.Request request) {
		List<Long> ids = new ArrayList<>();

		// request 순환하면서 해당 id에 맞는 카테고리 찾아온다.
		// 해당 카테고리의 순서를 수정한다.
		for (ReOrderCategory.OrderItem orderItem : request.getOrderItems()) {
			log.info("카테고리 가져오기");
			Category category = categoryProvider.findCategoryByIdOrThrow(orderItem.getId());

			log.info("수정 시작");
			category.updateCategoryOrder(orderItem.getNewOrder());

			ids.add(orderItem.getId());
		}

		return ReOrderCategory.Response.builder()
			.ids(ids)
			.build();
	}

	@Transactional
	public void deleteCategory(Long id) {
		Category category = categoryProvider.findCategoryByIdOrThrow(id);

		category.delete();
	}

}
