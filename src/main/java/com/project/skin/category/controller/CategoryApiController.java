package com.project.skin.category.controller;

import com.project.skin.category.service.dto.CategoryList;
import com.project.skin.category.service.dto.ReOrderCategory;
import com.project.skin.category.service.dto.UpdateCategory;
import com.project.skin.category.service.CategoryService;
import com.project.skin.category.service.dto.AddCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@RestController
public class CategoryApiController {
    private final CategoryService categoryService;

    @GetMapping
    public CategoryList.Response getCategories(@RequestParam(value = "parentId", required = false) Long parentId) {
        return categoryService.getCategories(parentId);
    }

    @PostMapping
    public AddCategory.Response createCategory(@RequestBody AddCategory.Request request) {
        return categoryService.saveCategory(request);
    }

    @PatchMapping("/{categoryId}")
    public UpdateCategory.Response updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategory.Request request) {
        return categoryService.updateCategory(categoryId,request);
    }

    @PatchMapping("/order") // 만약 동시에 순서를 변경하는 요청이 들어오면 순서가 꼬이는 경우가 발생하지 않을까요?
    public ReOrderCategory.Response reOrderCategories(@RequestBody ReOrderCategory.Request request) {
        return categoryService.reOrderCategories(request);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }
}
