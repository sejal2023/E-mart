package com.example.Repositories;

import com.example.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByCategory_Id(int id);



    List<Product> findBySubcategory_Id(int subcategoryid);

    List<Product> findByBrand(String brand);

    List<Product> findByIsdealTrue();
}
