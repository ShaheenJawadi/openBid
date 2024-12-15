package com.example.openbid.controller;


import com.example.openbid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String getAllUsers(){
        System.out.println("login");
        return "auth/login";
    }
}
