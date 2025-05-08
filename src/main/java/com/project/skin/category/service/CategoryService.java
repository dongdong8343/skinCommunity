package com.project.skin.category.service;

import com.project.skin.category.dto.SaveCategory;
import com.project.skin.category.provider.CategoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


}
