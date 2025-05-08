package com.project.skin.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminViewController {
    @GetMapping
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/categories")
    public String getCategoryManagementPage() {
        return "admin/categories";
    }
}
