package com.example.Models;

import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false, unique = true)
    private int id;

    @Column(nullable = false)
    private String productname;

    private String description;
    private double rating;
    private int stocks;
    private String brand;
    
    private String image;

    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}



	@ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategoryid")
    private Subcategory subcategory;

    private double discount;
    private double price;
    private boolean isdeal = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
//	public int getProductid() {
//		return productid;
//	}
//	public void setProductid(int productid) {
//		this.productid = productid;
//	}
//    
    
	public String getProductname() {
		return productname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getStocks() {
		return stocks;
	}
	public void setStocks(int stocks) {
		this.stocks = stocks;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Subcategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isIsdeal() {
		return isdeal;
	}
	public void setIsdeal(boolean isdeal) {
		this.isdeal = isdeal;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



//===============================================================================



    
    @Column
	private String productname_en;  // English translation
	
	@Column
	private String productname_fr;  // French translation
	
	@Column
	private String description_en;  // English description
	
	@Column
	private String description_fr;  // French description
	// Other fields ...
	
	public String getProductname(Locale locale) {
	    return locale.getLanguage().equals("fr") ? productname_fr : productname_en;
	}
	
	public String getDescription(Locale locale) {
	    return locale.getLanguage().equals("fr") ? description_fr : description_en;
	}
}


//=======================================================================================


