package com.example.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false, unique = true)
    private int id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password  is required")
    @Column(nullable = false)
    private String passwordHash;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    @Column(unique = true)
    private String phonenumber;

    @Column(name = "is_loyalty", nullable = false)
    private boolean isLoyalty = false;
    private int supercoin = 0;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public User() {
    }

    public User(String username, String email, String passwordHash, String phonenumber) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phonenumber = phonenumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public boolean isLoyalty() {
        return isLoyalty;
    }

    public void setLoyalty(boolean loyalty) {
        isLoyalty = loyalty;
    }

    public int getSupercoin() {
        return supercoin;
    }

    public void setSupercoin(int supercoins) {
        this.supercoin = supercoins;
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
}