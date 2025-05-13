package com.project.skin.category.service;

import com.project.skin.category.dto.ReadCategory;
import com.project.skin.category.dto.SaveCategory;
import com.project.skin.category.entity.Category;
import com.project.skin.category.provider.CategoryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
    public ReadCategory.Response getCategories() {
        ReadCategory.Response response = new ReadCategory.Response();

        List<Category> categories = categoryProvider.getCategories();

        for (Category category : categories) {
            log.info(category.getId());
        }

        // category dto를 리스트로 만든다.
        List<ReadCategory.CategoryDetail> categoryDetails = new ArrayList<>();

        categories.forEach(category -> {
            categoryDetails.add(ReadCategory.CategoryDetail.builder()
                    .id(category.getId())
                    .parentId(category.getParent() == null ? null : category.getParent().getId())
                    .code(category.getCode())
                    .name(category.getName())
                    .categoryOrder(category.getCategoryOrder())
                    .showSkinFilter(category.getShowSkinFilter())
                    .build());
        });

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
