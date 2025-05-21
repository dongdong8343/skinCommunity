package com.project.skin.category.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.project.skin.category.dto.AddCategory;
import com.project.skin.category.dto.ReOrderCategory;
import com.project.skin.category.dto.UpdateCategory;
import com.project.skin.category.entity.Category;
import com.project.skin.category.provider.CategoryProvider;
import com.project.skin.global.error.exception.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
public class CategoryService {
	private final CategoryProvider categoryProvider;

    @Transactional
    public SaveCategory.Response saveCategory(List<SaveCategory.Request> request) {
        SaveCategory.Response response = new SaveCategory.Response();

        for (SaveCategory.Request saveCategoryDto : request) {
            response.addCategoryId(
                    categoryProvider.saveCategory(
                            SaveCategory.buildCategory(saveCategoryDto, saveCategoryDto.getChildren())
                            ).getId()
            );
        }

        return response;
    }

	@Transactional
	public UpdateCategory.Response updateCategory(UpdateCategory.Request request) {
		categoryProvider.findCategoryByCode(request.getCode());

		Category category = categoryProvider.findCategoryById(request.getId()).orElseThrow(CategoryNotFoundException::new);

		// parentId로 newParent 찾기
		Category newParent = null;
		if(Objects.nonNull(request.getParentId())) {
			newParent = categoryProvider.findCategoryById(request.getParentId()).orElseThrow(CategoryNotFoundException::new);
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

        // map에 다 집어넣는다.
        HashMap<Long, ReadCategory.CategoryDetail> categoryDetailHashMap = new HashMap<>();

        categoryDetails.forEach(categoryDetail -> {
            categoryDetailHashMap.put(categoryDetail.getId(), categoryDetail);
        });

        // 리스트에 순서대로 집어넣는다.
        for (ReadCategory.CategoryDetail categoryDetail : categoryDetails) {
            Long parentId = categoryDetail.getParentId();

            if (Objects.isNull(parentId)) {
                response.getCategoryTree().add(categoryDetail);
            } else {
                ReadCategory.CategoryDetail parentCategoryDetail = categoryDetailHashMap.get(parentId);
                parentCategoryDetail.getChildren().add(categoryDetail);
            }
        }

        return response;
    }



}
