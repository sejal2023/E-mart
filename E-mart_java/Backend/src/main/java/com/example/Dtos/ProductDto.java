package com.example.Dtos;

public class ProductDto {
    private int id;
    private String productname;
    private String description;
    private double rating;
    private int stocks;
    private String brand;
    private int categoryId;
    private String categoryname;
    private String categoryDescription;
//    private String categoryimage;
    private int subcategoryId;
    private String subcategoryname;
//    private String subcategoryimage;
    private double discount;
    private double price;
    private boolean isdeal;
    private String image;
//    private String createdAt;
//    private String updatedAt;
    
    

    // Getters and setters
    public int getId() {
        return id;
    }
    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
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

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

//    public String getCategoryimage() {
//        return categoryimage;
//    }
//    public void setCategoryimage(String categoryimage) {
//        this.categoryimage = categoryimage;
//    }

    public int getSubcategoryId() {
        return subcategoryId;
    }
    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }
    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }

//    public String getSubcategoryimage() {
//        return subcategoryimage;
//    }
//    public void setSubcategoryimage(String subcategoryimage) {
//        this.subcategoryimage = subcategoryimage;
//    }

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

//    public String getCreatedAt() {
//        return createdAt;
//    }
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }
}

