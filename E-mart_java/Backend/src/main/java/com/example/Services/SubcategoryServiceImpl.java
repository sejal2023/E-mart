package com.example.Services;

import com.example.Models.Category;
import com.example.Models.Subcategory;
import com.example.Repositories.CategoryRepository;
import com.example.Repositories.SubcategoryRepository;
import com.example.Services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public Optional<Subcategory> getSubcategoryById(int id) {
        return subcategoryRepository.findById(id);
    }

    @Override
    public List<Subcategory> getSubcategoriesByCategory(int categoryId) {
        return subcategoryRepository.findByCategory_Id(categoryId);
    }

//    @Override
//    public Subcategory createSubcategory(Subcategory subcategory) {
//        // Ensure the category exists before adding a subcategory
//        Category category = categoryRepository.findById(subcategory.getCategory().getId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        subcategory.setCategory(category);
//        return subcategoryRepository.save(subcategory);
//    }
//
//    @Override
//    public Subcategory updateSubcategory(int id, Subcategory subcategory) {
//        return subcategoryRepository.findById(id).map(existingSubcategory -> {
//            existingSubcategory.setSubcategoryname(subcategory.getSubcategoryname());
//            existingSubcategory.setSubcategoryimage(subcategory.getSubcategoryimage());
//            return subcategoryRepository.save(existingSubcategory);
//        }).orElseThrow(() -> new RuntimeException("Subcategory not found"));
//    }
//
//    @Override
//    public void deleteSubcategory(int id) {
//        subcategoryRepository.deleteById(id);
//    }
}
