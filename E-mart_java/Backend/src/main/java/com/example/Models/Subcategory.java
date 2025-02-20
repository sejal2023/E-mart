package com.example.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategoryid", nullable = false, unique = true)
    private int id;

    @NotBlank(message = "Subcategory name is required")
    @Size(min = 3, max = 255, message = "Subcategory name must be between 3 and 255 characters")
    @Column(nullable = false)
    private String subcategoryname;
    private String subcategoryimage;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;


    // Constructors
    public Subcategory() {
    }

    public Subcategory(String subcategoryname, String subcategoryDescription, String subcategoryimage, Category category) {
        this.subcategoryname = subcategoryname;
        this.category = category;
    }

    // Getters and Setters
//    public int getSubcategoryid() {
//        return subcategoryid;
//    }
//
//    public void setSubcategoryid(int subcategoryid) {
//        this.subcategoryid = subcategoryid;
//    }
    
    

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname;
    }


    public String getSubcategoryimage() {
        return subcategoryimage;
    }

    public void setSubcategoryimage(String subcategoryimage) {
        this.subcategoryimage = subcategoryimage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}