package com.example.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Models.CartItem;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    
    List<CartItem> findByCart_User_IdAndProduct_Id(int userId, int productId);

    List<CartItem> findByCart_Id(int cartId);

}

