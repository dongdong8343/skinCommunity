package com.project.skin.category.provider;

import java.util.List;

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

    public List<Category> getCategoriesByParentId(Long parentId) {
        return categoryRepository.findByParentIdNullable(parentId);
    }

    public Category findCategoryByCode(String code) {
        return categoryRepository.findByCode(code).orElseThrow(DuplicateCategoryCodeException::new);
    }

    public Category findCategoryByIdOrThrow(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
    }

    public Category findCategoryByIdOrNull(Long id) {
        return categoryRepository.findById(id)
            .orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
