package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Models.Cart;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	
    List<Cart> findByUser_Id(int userId);

	List<Cart> findByUserIdAndProductId(int userId, int id);
    

}
