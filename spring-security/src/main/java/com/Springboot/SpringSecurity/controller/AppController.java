package com.Springboot.SpringSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/home")
    public String home() {
        return "This is the Home Page";
    }

    @GetMapping("/products")
    public String products() {
        return "This is the Products Page";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "This is the Dashboard Page";
    }

    @GetMapping("/manage")
    public String manage() {
        return "This is the Manage Page";
    }
}
