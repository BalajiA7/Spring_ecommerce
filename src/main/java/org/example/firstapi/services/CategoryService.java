package org.example.firstapi.services;

import org.example.firstapi.models.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String categoryName);
    public List<Category> getAllCategories();
}
