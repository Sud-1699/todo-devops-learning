package com.learning.todo.welcome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class WelcomeController {
    @GetMapping("/test")
    public String performTest() {
        return "Welcome to Todo Backend Service";
    }
}
