package com.project.skin.repository.category;

import com.project.skin.category.entities.Category;
import com.project.skin.category.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@Log4j2
@SpringBootTest
@ActiveProfiles(profiles = "prod")
public class CategoryRepositoryTests {
    @Autowired(required = false)
    private CategoryRepository categoryRepository;

    @Test
    public void findByCodeTest() {
        Category category = Category.createCategory("aaa", "피부관리팁");

        categoryRepository.save(category);

        Category savedCategory = categoryRepository.findByCode(category.getCode())
                .orElseThrow(IllegalArgumentException::new);

        log.info(savedCategory);
    }
}
