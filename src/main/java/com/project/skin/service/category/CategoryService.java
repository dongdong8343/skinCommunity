package com.project.skin.service.category;

import com.project.skin.domain.category.Category;
import com.project.skin.provider.category.CategoryProvider;
import com.project.skin.service.dto.AddCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryProvider categoryProvider;

    @Transactional
    public AddCategory.Response createCategory(AddCategory.Request request) {
        Category category = categoryProvider.findCategoryByCode(request.getCategoryCode());

        // 이미 존재하는 서브 카테고리면 예외발생

        if (category == null) {
            category = Category.createCategory(request.getCategoryCode(), request.getCategoryName());
        }


        Category subCategory = Category.createSubCategory(category, request.getSubCategoryCode(), request.getSubCategoryName());

        Long categoryId = categoryProvider.saveCategory(category);

        return AddCategory.Response.createResponse(categoryId, subCategory.getId());
    }
}
