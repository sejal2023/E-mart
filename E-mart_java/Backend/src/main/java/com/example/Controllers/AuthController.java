package com.example.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Config.JwtProvider;
import com.example.Exceptions.UserNotFoundException;
import com.example.Repositories.UserRepository;
import com.example.Services.UserImpl;
import com.example.Services.UserService;
import com.example.Models.AuthResponse;
import com.example.Models.LoginRequest;
import com.example.Models.User;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository user_repo;
    
    @Autowired
    private  UserService userService;

    @Autowired
    private JwtProvider jwtprovider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserImpl userimpl;

//    @PostMapping("/signup")
//    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) {
//        logger.info("Received signup request for email: {}", user.getEmail());
//
//        String email = user.getEmail();
//        String password = user.getPasswordHash();
//        String username = user.getUsername();
//
//        User isEmailExist = user_repo.findByEmail(email);
//
//        if (isEmailExist != null) {
//            logger.warn("Signup attempt with an already registered email: {}", email);
//            throw new UserNotFoundException("Email is already registered");
//        }
//
//        User createdUser = new User();
//        createdUser.setEmail(email);
//        createdUser.setPasswordHash(passwordEncoder.encode(password));
//        createdUser.setUsername(username);
//
//        User savedUser = user_repo.save(createdUser);
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPasswordHash());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = jwtprovider.generateToken(authentication);
//
//        logger.info("Signup successful for email: {}", email);
//        return new ResponseEntity<>(new AuthResponse(token, "Signin success", user, true, 2000), HttpStatus.OK);
//    }
    
    
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) {
        logger.info("Received signup request for email: {}", user.getEmail());

        String email = user.getEmail();
        String password = user.getPasswordHash();
        String username = user.getUsername();

        User isEmailExist = user_repo.findByEmail(email);
        if (isEmailExist != null) {
            logger.warn("Signup attempt with an already registered email: {}", email);
        }

        // Log received values
        logger.info("Received isLoyalty: {}, supercoin: {}", user.isLoyalty(), user.getSupercoin());

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPasswordHash(passwordEncoder.encode(password));
        createdUser.setUsername(username);
        createdUser.setLoyalty(user.isLoyalty());  // Assign from request
        createdUser.setSupercoin(user.getSupercoin());  // Assign from request

        User savedUser = user_repo.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPasswordHash());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtprovider.generateToken(authentication);

        logger.info("Signup successful: isLoyalty = {}, supercoin = {}", savedUser.isLoyalty(), savedUser.getSupercoin());
        return new ResponseEntity<>(new AuthResponse(token, "Signup success", savedUser), HttpStatus.CREATED);
    }



//    @PostMapping("/signin")
//    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginrequest) {
//        logger.info("Received login request for email: {}", loginrequest.getEmail());
//
//        String username = loginrequest.getEmail();
//        String password = loginrequest.getPassword();
//
//        try {
//            Authentication authentication = authenticate(username, password);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = jwtprovider.generateToken(authentication);
//
//            User user = user_repo.findByEmail(username);
//            if (user == null) {
//                logger.error("User not found for email: {}", username);
//                throw new UserNotFoundException("User not found");
//            }
//
//            logger.info("Login successful for email: {}", username);
//            return new ResponseEntity<>(new AuthResponse(token, "Signin success", user), HttpStatus.OK);
//        } catch (BadCredentialsException ex) {
//            logger.error("Login failed for email: {} - Reason: {}", username, ex.getMessage());
//            throw ex;
//        }
//    }
    
    
    
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginrequest) {
        logger.info("Received login request for email: {}", loginrequest.getEmail());

        String username = loginrequest.getEmail();
        String password = loginrequest.getPassword();

        
            Authentication authentication = authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtprovider.generateToken(authentication);

            User user = user_repo.findByEmail(username);
            

            logger.info("Login successful for email: {}", username);
            return new ResponseEntity<>(new AuthResponse(token, "Signin success", user), HttpStatus.OK);
        } 


    private Authentication authenticate(String username, String password) {
        logger.debug("Authenticating user: {}", username);

        UserDetails userdetails = userimpl.loadUserByUsername(username);
        if (userdetails == null) {
            logger.error("Authentication failed: User not found - {}", username);
            
        }


        logger.debug("User authenticated successfully: {}", username);
        return new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
    }
    
    @PutMapping("/update-supercoin/{userId}")
    public ResponseEntity<String> updateSuperCoinBalance(
            @PathVariable int userId, 
            @RequestBody SuperCoinRequest request) {
        
        boolean isUpdated = userService.updateSuperCoinBalance(userId, request.getSupercoin());

        if (isUpdated) {
            return ResponseEntity.ok("SuperCoin balance updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update SuperCoin balance.");
        }
    }
    
    
    
    
    
    
    public static class SuperCoinRequest {
        private int supercoin;
        public int getSupercoin() { return supercoin; }
        public void setSupercoin(int supercoin) { this.supercoin = supercoin; }
    }
}
