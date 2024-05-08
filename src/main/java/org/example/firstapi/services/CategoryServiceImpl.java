package org.example.firstapi.services;

import org.example.firstapi.Repositories.CategoryRepository;
import org.example.firstapi.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByNameIgnoreCase(categoryName);
        if (categoryOptional.isEmpty()) {
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
