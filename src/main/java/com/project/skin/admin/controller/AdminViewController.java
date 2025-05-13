package com.project.skin.admin.controller;

import com.project.skin.category.dto.ReadCategory;
import com.project.skin.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminViewController {
    private final CategoryService categoryService;

    @GetMapping
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/categories")
    public String getCategoryManagementPage(Model model) {
        ReadCategory.Response categoryTree = categoryService.getCategories();

        model.addAttribute("categoryTree", categoryTree.getCategoryTree());

        return "admin/categories";
    }
}
