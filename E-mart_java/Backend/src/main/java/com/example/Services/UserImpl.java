package com.example.Services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Models.User;
import com.example.Repositories.UserRepository;



@Service
public class UserImpl implements UserDetailsService {



    @Autowired
    private UserRepository user_repo; 

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = user_repo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found" + username);
        }

        List<GrantedAuthority> authority = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), authority);
    }
}

