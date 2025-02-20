package com.example.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Models.Subcategory;
import com.example.Services.SubcategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subcategories")
@CrossOrigin(origins = "http://localhost:5173")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping
    public List<Subcategory> getAllSubcategories() {
        return subcategoryService.getAllSubcategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable int id) {
        Optional<Subcategory> subcategory = subcategoryService.getSubcategoryById(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public List<Subcategory> getSubcategoriesByCategory(@PathVariable int categoryId) {
        return subcategoryService.getSubcategoriesByCategory(categoryId);
    }

}
