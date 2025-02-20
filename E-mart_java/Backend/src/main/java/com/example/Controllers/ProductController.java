//package com.example.Controllers;
//
//import com.example.Models.Product;
//import com.example.Services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:5173")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/{productid}")
//    public ResponseEntity<Product> getProductById(@PathVariable int productid) {
//        Optional<Product> product = productService.getProductById(productid);
//        
//        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/categories/{categoryid}")
//    public List<Product> getProductsByCategory(@PathVariable int categoryid) {
//        return productService.getProductsByCategory(categoryid);
//    }
//
//    @GetMapping("/subcategories/{subcategoryid}")
//    public List<Product> getProductsBySubcategory(@PathVariable int subcategoryid) {
//        return productService.getProductsBySubcategory(subcategoryid);
//    }
//
////    @GetMapping("/brand/{brand}")
////    public List<Product> getProductsByBrand(@PathVariable String brand) {
////        return productService.getProductsByBrand(brand);
////    }
////
////    @GetMapping("/deals")
////    public List<Product> getDeals() {
////        return productService.getDeals();
////    }
//}




//=============================================================================================================




//i18n

//
//package com.example.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.Models.Product;
//import com.example.Services.ProductService;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:5173")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
//        Locale locale = new Locale(language);
//
//        // Get translated messages
//        String productName = messageSource.getMessage("product.name", null, locale);
//        String productDescription = messageSource.getMessage("product.description", null, locale);
//
//        // Your logic here to fetch products
//        List<Product> products = productService.getAllProducts();
//
//        // Optionally, you can set the translated text to the response (like setting i18n content for the product)
//        products.forEach(product -> {
//            product.setProductname(productName);
//            product.setDescription(productDescription);
//        });
//
//        return ResponseEntity.ok(products);
//    }
//    
//    @GetMapping("/{productid}")
//	  public ResponseEntity<Product> getProductById(@PathVariable int productid) {
//	      Optional<Product> product = productService.getProductById(productid);
//	      
//	      return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//	  }
//	
//	  @GetMapping("/categories/{categoryid}")
//	  public List<Product> getProductsByCategory(@PathVariable int categoryid) {
//	      return productService.getProductsByCategory(categoryid);
//	  }
//	
//	  @GetMapping("/subcategories/{subcategoryid}")
//	  public List<Product> getProductsBySubcategory(@PathVariable int subcategoryid) {
//	      return productService.getProductsBySubcategory(subcategoryid);
//	  }
//}


//====================================================================================================

//dto



package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Models.Product;
import com.example.Services.ProductService;
import com.example.Dtos.ProductDto;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    
    private ProductDto convertToProductDto(Product product, Locale locale) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
//        productDto.setProductname(messageSource.getMessage("product.name", null, locale));  
//        productDto.setDescription(messageSource.getMessage("product.description", null, locale)); 
        
        productDto.setProductname(
                messageSource.getMessage("product." + product.getProductname(), null, product.getProductname(), locale)
            );
            productDto.setDescription(
                messageSource.getMessage("product." + product.getDescription(), null, product.getDescription(), locale)
            );
        productDto.setRating(product.getRating());
        productDto.setStocks(product.getStocks());
        productDto.setBrand(product.getBrand());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryname(product.getCategory().getCategoryname());
        productDto.setSubcategoryId(product.getSubcategory().getId());
        productDto.setSubcategoryname(product.getSubcategory().getSubcategoryname());
        productDto.setDiscount(product.getDiscount());
        productDto.setPrice(product.getPrice());
        productDto.setIsdeal(product.isIsdeal());
        productDto.setImage(product.getImage());


        return productDto;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
        Locale locale = new Locale(language);

        
        List<Product> products = productService.getAllProducts();

        
        List<ProductDto> productDtos = products.stream()
                .map(product -> convertToProductDto(product, locale))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{productid}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int productid, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
                                                     
        Locale locale = new Locale(language);

        
        Product product = productService.getProductById(productid).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        ProductDto productDto = convertToProductDto(product, locale);

        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/categories/{categoryid}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable int categoryid, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
                                                                  
        Locale locale = new Locale(language);

        
        List<Product> products = productService.getProductsByCategory(categoryid);

        
        List<ProductDto> productDtos = products.stream()
                .map(product -> convertToProductDto(product, locale))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/subcategories/{subcategoryid}")
    public ResponseEntity<List<ProductDto>> getProductsBySubcategory(@PathVariable int subcategoryid,  @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
                                                                   
        Locale locale = new Locale(language);

        
        List<Product> products = productService.getProductsBySubcategory(subcategoryid);

        
        List<ProductDto> productDtos = products.stream()
                .map(product -> convertToProductDto(product, locale))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }
}


