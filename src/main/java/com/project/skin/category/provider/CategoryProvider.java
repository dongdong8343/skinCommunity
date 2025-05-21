package com.project.skin.category.provider;

import com.project.skin.category.entity.Category;
import com.project.skin.category.repository.CategoryRepository;
import com.project.skin.global.error.exception.DuplicateCategoryCodeException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryProvider {
    private final CategoryRepository categoryRepository;

    public Category findCategoryByCode(String code) {
        return categoryRepository.findByCode(code).orElseThrow(DuplicateCategoryCodeException::new);
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
