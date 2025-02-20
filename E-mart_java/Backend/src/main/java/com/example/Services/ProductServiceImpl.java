package com.example.Services;

import com.example.Models.Product;
import com.example.Repositories.ProductRepository;
import com.example.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(int productid) {
        return productRepository.findById(productid);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryid) {
        return productRepository.findByCategory_Id(categoryid);
    }

    @Override
    public List<Product> getProductsBySubcategory(int subcategoryid) {
        return productRepository.findBySubcategory_Id(subcategoryid);
    }

//    @Override
//    public List<Product> getProductsByBrand(String brand) {
//        return productRepository.findByBrand(brand);
//    }
//
//    @Override
//    public List<Product> getDeals() {
//        return productRepository.findByIsdealTrue();
//    }

}
