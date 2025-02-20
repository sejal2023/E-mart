package com.example.Services;

import com.example.Models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(int productid);

    List<Product> getProductsByCategory(int categoryid);

    List<Product> getProductsBySubcategory(int subcategoryid);

//    List<Product> getProductsByBrand(String brand);
//
//    List<Product> getDeals();


}
