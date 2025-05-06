package com.project.skin.category.controller;

import com.project.skin.category.service.CategoryService;
import com.project.skin.category.dto.AddCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping
    public AddCategory.Response createCategory(@RequestBody AddCategory.Request request) {
        return categoryService.createCategory(request);
    }
}
