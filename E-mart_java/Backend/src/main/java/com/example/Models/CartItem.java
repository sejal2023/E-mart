package com.example.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cartitems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitemid", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cartid", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    private int quantity;
    private LocalDateTime addedAt;
    
    
//    // Getters and Setters
//	public int getCartItemid() {
//		return cartItemid;
//	}
//	public void setCartItemid(int cartItemid) {
//		this.cartItemid = cartItemid;
//	}
    
    
	public Cart getCart() {
		return cart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getAddedAt() {
		return addedAt;
	}
	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}

 
    
    
}