package com.project.skin.category.provider;

import com.project.skin.category.entities.Category;
import com.project.skin.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryProvider {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findCategoryByCode(String code) {
        return categoryRepository.findByCode(code);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


        return categoryRepository.save(category).getId();
    }
}
