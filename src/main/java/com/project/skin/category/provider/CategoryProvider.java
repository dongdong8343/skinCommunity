package com.project.skin.category.provider;

import java.util.List;
import java.util.Optional;

import com.project.skin.category.entity.Category;
import com.project.skin.category.repository.CategoryRepository;
import com.project.skin.global.error.exception.CategoryNotFoundException;
import com.project.skin.global.error.exception.DuplicateCategoryCodeException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryProvider {
    private final CategoryRepository categoryRepository;

    public List<Category> getParentCategories() {
        return categoryRepository.findCategoriesByParentIsNullAndDeletedAtIsNull();
    }

    public List<Category> getCategoriesByParentId(Long parentId) {
        return categoryRepository.findByParentIdNullable(parentId);
    }

    public Optional<Category> findCategoryByCode(String code) { //수정 필요 -> 없으면 duplicate 하도록 하고있음...
        return categoryRepository.findByCode(code);
    }

    public Category findCategoryByIdOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(CategoryNotFoundException::new);
    }

    public Category findCategoryByIdOrNull(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
