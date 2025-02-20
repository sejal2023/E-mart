package com.example.Services;

import com.example.Models.Cart;
import java.util.List;

public interface CartService {
	
    String addToCart(Cart cart);
    
    String removeFromCart(int cartId);
    
    String updateCartItem(int cartId, int quantity);
    
    List<Cart> viewCart(int userId);
}
