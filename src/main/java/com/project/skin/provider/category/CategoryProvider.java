package com.project.skin.provider.category;

import com.project.skin.domain.category.Category;
import com.project.skin.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryProvider {
    private final CategoryRepository categoryRepository;

    public Category findCategoryByCode(String code) {
        return categoryRepository.findByCode(code)
                .orElse(null);
    }

    public Long saveCategory(Category category) {
        return categoryRepository.save(category).getId();
    }
}
