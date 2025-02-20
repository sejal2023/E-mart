package com.example.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Models.Cart;
import com.example.Models.Product;
import com.example.Models.User;
import com.example.Repositories.CartRepository;
import com.example.Repositories.ProductRepository;
import com.example.Repositories.UserRepository;
import com.example.Services.CartService;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository; 
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository; 

    
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestBody Cart cart) {
        

        Optional<User> user = userRepository.findById(cart.getUser().getId());
        Optional<Product> product = productRepository.findById(cart.getProduct().getId());

        List<Cart> existingCartItems = cartRepository.findByUserIdAndProductId(cart.getUser().getId(), cart.getProduct().getId());
        if (existingCartItems != null && !existingCartItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is already in the cart!");
        }

        cart.setUser(user.get());
        cart.setProduct(product.get());
        cartService.addToCart(cart);

        return ResponseEntity.ok().body("Product added to cart!");
    }




    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeFromCart(@PathVariable int cartId) {
        String response = cartService.removeFromCart(cartId);
        return ResponseEntity.ok().body(response);
    }


    
    @PutMapping("/update/{cartId}")
    public ResponseEntity<String> updateCartItem(@PathVariable int cartId, @RequestParam int quantity) {
        String response = cartService.updateCartItem(cartId, quantity);
        return ResponseEntity.ok().body(response);
    }



    @GetMapping("/view/{userId}")
    public ResponseEntity<List<Cart>> viewCart(@PathVariable int userId) {
        List<Cart> cartItems = cartService.viewCart(userId);
        return ResponseEntity.ok().body(cartItems);
    }
}








