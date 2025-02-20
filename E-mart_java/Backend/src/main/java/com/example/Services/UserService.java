package com.example.Services;


import java.util.List;

import com.example.Models.User;

public interface UserService {
	public User createUser(User user);
	
	public User findUserByEmail(String email);
	
	public User validateUserCredentials(String email, String password);
	
	public List<User> getAllUsers();
	
	boolean updateSuperCoinBalance(int userId, int newBalance);
	
//	public User updateUser(int userId, User userDetails);
//	
//	public void deleteUser(int userId);
//	
}
