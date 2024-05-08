package org.example.firstapi.controllers;

import org.example.firstapi.models.Category;
import org.example.firstapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /* GET REQUEST */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
