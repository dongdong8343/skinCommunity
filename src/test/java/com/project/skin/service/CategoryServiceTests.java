package com.project.skin.service;

import com.project.skin.service.category.CategoryService;
import com.project.skin.service.dto.AddCategory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Log4j2
@SpringBootTest
@ActiveProfiles(profiles = "prod")
public class CategoryServiceTests {
    @Autowired(required = false)
    private CategoryService categoryService;

    @Test
    public void createCategoryTest() {
        AddCategory.Request request = new AddCategory.Request("날씨별 관리", "weather", "여름", "summer");
        AddCategory.Response response = categoryService.createCategory(request);

        log.info("caid---------------------" + response.getCategoryId());
        log.info("subid----------------------" + response.getSubCategoryId());
    }
}
