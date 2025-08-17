package com.example.shcoolManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shcoolManager.model.User;
import com.example.shcoolManager.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody User user){
        authService.Register(user);
    }
    @PostMapping("/login")
    public void login(@RequestBody User user){
        authService.Login(user);
    }
}
