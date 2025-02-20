package com.example.Services;

import com.example.Models.Subcategory;
import java.util.List;
import java.util.Optional;

public interface SubcategoryService {
    List<Subcategory> getAllSubcategories();
    
    Optional<Subcategory> getSubcategoryById(int id);
    
    List<Subcategory> getSubcategoriesByCategory(int categoryId);
    
//    Subcategory createSubcategory(Subcategory subcategory);
//    
//    Subcategory updateSubcategory(int id, Subcategory subcategory);
//    
//    void deleteSubcategory(int id);
}
