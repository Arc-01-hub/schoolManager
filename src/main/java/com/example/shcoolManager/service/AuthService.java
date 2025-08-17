package com.example.shcoolManager.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shcoolManager.enums.Role;
import com.example.shcoolManager.model.User;
import com.example.shcoolManager.repo.UserRepo;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User Register(User user){
        if(userRepo.findByUserName(user.getUserName()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        User  newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setType(user.getType());
        
        Set<Role> roles = new HashSet<>();
        if("admin".equals(user.getType())){
            roles.add(Role.ADMIN);       
        }
        else if("teacher".equals(user.getType())){
            roles.add(Role.TEACHER);       
        }
        else{
            roles.add(Role.STUDENT);
        }
        newUser.setRoles(roles);
        User savedUser = userRepo.save(newUser);
        return savedUser;
    }
    public String Login(User user){
        try{
            // Authentication auth = 
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

                return "Login Succesfully";
        }catch(Exception e){    
            return "Somthing Wrong";
        }
    }

}
