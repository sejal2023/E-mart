//package com.example.Controllers;
//
//
//import com.example.Models.Category;
//import com.example.Services.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import org.apache.log4j.Logger;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/categories")
//@CrossOrigin(origins = "http://localhost:5173")
//public class CategoryController {
//	
//	final static Logger logger = Logger.getLogger(CategoryController.class);
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping
//    public List<Category> getAllCategories() {
//    	logger.info("Fetching all categories");
//    	
////        return categoryService.getAllCategories();
//    	
//    	List<Category> categories = categoryService.getAllCategories();
//    	
//        
//    	logger.info("Successfully fetched " + categories.size() + " categories.");
//        
//        return categories;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
//        Optional<Category> category = categoryService.getCategoryById(id);
//        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//}
//
//



package com.example.Controllers;

import com.example.Models.Category;
import com.example.Services.CategoryService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
	
	private static final Logger logger = LogManager.getLogger(CategoryController.class);
	
	

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        logger.info("Fetching all categories");

        List<Category> categories = categoryService.getAllCategories();

        logger.info("Successfully fetched " + categories.size() + " categories.");

        return categories;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        logger.info("Fetching category with id: " + id);
        
        Optional<Category> category = categoryService.getCategoryById(id);

        if (category.isPresent()) {
            logger.info("Category found: " + category.get());
            return ResponseEntity.ok(category.get());
        } else {
            logger.warn("Category with id " + id + " not found.");
            return ResponseEntity.notFound().build();
        }
    }
}

