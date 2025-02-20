package com.example.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exceptions.UserNotFoundException;
import com.example.Models.User;
import com.example.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
    	user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
//                .orElseThrow(() -> new UserNotFoundException("Incorrect email or password"));
    }
    
    
    public User validateUserCredentials(String email, String password) {
        User user = findUserByEmail(email);

        // Validate hashed password
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new UserNotFoundException("Incorrect email or password");
        }

        return user;
    }
    
    
    @Override
    public boolean updateSuperCoinBalance(int userId, int newBalance) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSupercoin(newBalance);
            userRepository.save(user);
            return true;
        }
        return false;
    }

//    public User updateUser(int userId, User userDetails) {
//        User user = userRepository.findById(userId).orElse(null);
//        if (user != null) {
//            user.setUsername(userDetails.getUsername());
//            user.setEmail(userDetails.getEmail());
//            user.setPasswordHash(userDetails.getPasswordHash());
//            user.setPhonenumber(userDetails.getPhonenumber());
//            user.setLoyalty(userDetails.isLoyalty());
//            user.setSupercoins(userDetails.getSupercoins());
//            return userRepository.save(user);
//        }
//        return null;
//    }
//
//    public void deleteUser(int userId) {
//        userRepository.deleteById(userId);
//    }
}