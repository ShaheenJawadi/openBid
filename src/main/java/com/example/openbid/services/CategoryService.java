package com.example.openbid.services;

import com.example.openbid.model.Category;
import com.example.openbid.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

  interface CategoryServiceInterface {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
}

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}