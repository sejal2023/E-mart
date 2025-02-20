package com.example.Models;

//public class AuthResponse {
//    private String jwt;
//    private String message;
//    private User user;  // Added user object
//
//    public AuthResponse(String jwt, String message, User user) {
//        this.jwt = jwt;
//        this.message = message;
//        this.user = user;
//    }
//
//    public String getJwt() {
//        return jwt;
//    }
//
//    public void setJwt(String jwt) {
//        this.jwt = jwt;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

//public User getUser() {
//    return user;
//}
//
//public void setUser(User user) {
//    this.user = user;
//}
//}



//======================================================




public class AuthResponse {
    private String token;
    private String message;
    private User user;
//    private boolean isLoyalty;
//    private int supercoin;

    public AuthResponse(String token, String message, User user) {
        this.token = token;
        this.message = message;
        this.user = user;
//        this.isLoyalty = isLoyalty;
//        this.supercoin = supercoin;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public boolean isLoyalty() {
//        return isLoyalty;
//    }
//
//    public void setLoyalty(boolean isLoyalty) {
//        this.isLoyalty = isLoyalty;
//    }
//
//    public int getSupercoin() {
//        return supercoin;
//    }
//
//    public void setSupercoin(int supercoin) {
//        this.supercoin = supercoin;
//    }
}

    
