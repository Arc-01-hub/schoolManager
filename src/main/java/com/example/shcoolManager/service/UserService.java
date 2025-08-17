package com.example.shcoolManager.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.model.User;
import com.example.shcoolManager.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepo.findByUserName(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            user.getRoles().stream()
            .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
            .collect(Collectors.toList())
            );
    }
}
