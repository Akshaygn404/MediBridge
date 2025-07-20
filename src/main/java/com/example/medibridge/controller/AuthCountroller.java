package com.example.medibridge.controller;

import com.example.medibridge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthCountroller {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String home(){
        return "Welcome to pharma";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,@RequestParam String email,@RequestParam String password,@RequestParam String phone,@RequestParam String role){

        return (authService.registerNewUser(name,email,password,phone,role))?"Successfully registered":"User already exist";
    }


    @GetMapping("/dummy")
    public String dummy() {
        return " Well;smnf";
    }
}
