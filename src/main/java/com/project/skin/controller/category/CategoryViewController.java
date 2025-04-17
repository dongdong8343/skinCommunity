package com.project.skin.controller.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("categories")
@Controller
public class CategoryViewController {
    @GetMapping("/new")
    public String createCategory() {
        return "categories/new";
    }
}
