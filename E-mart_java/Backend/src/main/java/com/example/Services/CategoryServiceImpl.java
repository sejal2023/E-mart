package com.example.Services;


import com.example.Models.Category;
import com.example.Repositories.CategoryRepository;
import com.example.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

//    @Override
//    public Category createCategory(Category category) {
//        category.setCreatedAt(LocalDateTime.now());
//        category.setUpdatedAt(LocalDateTime.now());
//        return categoryRepository.save(category);
//    }
//
//    @Override
//    public Category updateCategory(int id, Category category) {
//        return categoryRepository.findById(id).map(existingCategory -> {
//            existingCategory.setCategoryname(category.getCategoryname());
//            existingCategory.setCategoryDescription(category.getCategoryDescription());
//            existingCategory.setCategoryimage(category.getCategoryimage());
//            existingCategory.setUpdatedAt(LocalDateTime.now());
//            return categoryRepository.save(existingCategory);
//        }).orElseThrow(() -> new RuntimeException("Category not found"));
//    }
//
//    @Override
//    public void deleteCategory(int id) {
//        categoryRepository.deleteById(id);
//    }
}
