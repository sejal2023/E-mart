package com.example.demo;

import com.example.Models.Category;
import com.example.Repositories.CategoryRepository;
import com.example.Services.CategoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category1, category2;

    @BeforeEach
    void setUp() {
        category1 = new Category();
        category1.setId(1);
        category1.setCategoryname("Electronics");
        category1.setCategoryDescription("Devices and Gadgets");
        category1.setCategoryimage("electronics.png");
        category1.setCreatedAt(LocalDateTime.now());
        category1.setUpdatedAt(LocalDateTime.now());

        category2 = new Category();
        category2.setId(2);
        category2.setCategoryname("Clothing");
        category2.setCategoryDescription("Men and Women Apparel");
        category2.setCategoryimage("clothing.png");
        category2.setCreatedAt(LocalDateTime.now());
        category2.setUpdatedAt(LocalDateTime.now());
    } 

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));

        Optional<Category> category = categoryService.getCategoryById(1);

        assertTrue(category.isPresent());
//        assertTrue(Optional.empty() != null);
        assertEquals("Electronics", category.get().getCategoryname());
        verify(categoryRepository, times(1)).findById(1);
    }
    
    
    @Test
    void testGetAllCategories_EmptyList() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<Category> categories = categoryService.getAllCategories();

        assertTrue(categories.isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }
    
    
    
    
    
    @Test
    void testGetCategoryById_InvalidId() {
        when(categoryRepository.findById(9)).thenReturn(Optional.empty());

        Optional<Category> category = categoryService.getCategoryById(9);

        assertTrue(category.isPresent());
        verify(categoryRepository, times(1)).findById(9);
    }
    
}
