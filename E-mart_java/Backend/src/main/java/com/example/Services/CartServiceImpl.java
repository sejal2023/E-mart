package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Models.Cart;
import com.example.Repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public String addToCart(Cart cart) {
        cartRepository.save(cart);
        return "Item added to cart successfully!";
    }

    @Override
    public String removeFromCart(int cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return "Item removed from cart successfully!";
        }
        return "Cart item not found!";
    }

    @Override
    public String updateCartItem(int cartId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setQuantity(quantity);
            cartRepository.save(cart);
            return "Cart item updated successfully!";
        }
        return "Cart item not found!";
    }

    @Override
    public List<Cart> viewCart(int userId) {
        return cartRepository.findByUser_Id(userId);
    }
}
