package com.project.skin.category.service;

import com.project.skin.category.entities.Category;
import com.project.skin.category.provider.CategoryProvider;
import com.project.skin.category.dto.AddCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryProvider categoryProvider;

    @Transactional
    public AddCategory.Response createCategory(AddCategory.Request request) {
        Category category = categoryProvider.findCategoryByCode(request.getCategoryCode())
                .orElse(null);

        if (Objects.isNull(category)) {
            category = Category.createCategory(request.getCategoryCode(), request.getCategoryName());
        }

        //Category subCategory = category.addSubCategory(category, request.getSubCategoryCode(), request.getSubCategoryName());

//        category.addSubCategory(Category.createCategory(request.getSubCategoryCode(), request.getCategoryName()));
//
//        Long categoryId = categoryProvider.saveCategory(category);
//
//        return AddCategory.Response.createResponse(categoryId, subCategory.getId());

        return AddCategory.Response.createResponse(category.getId(), category.getId());
    }


}
