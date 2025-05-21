package com.project.skin.category.controller;

import com.project.skin.category.dto.ReadCategory;
import com.project.skin.category.service.CategoryService;
import com.project.skin.category.dto.SaveCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @GetMapping
    public ReadCategory.Response getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public SaveCategory.Response createCategory(@RequestBody List<SaveCategory.Request> request) {

        log.info(request.toString());

        return categoryService.saveCategory(request);
    }

    @PatchMapping
    public UpdateCategory.Response updateCategory(@RequestBody UpdateCategory.Request request) {
        return categoryService.updateCategory(request);
    }

}
