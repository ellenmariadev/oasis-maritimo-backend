package com.example.oasismaritimo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class WelcomeController {

    @GetMapping
    public Map<String, String> welcome() {
        Map<String, String> welcomeMessage = new HashMap<>();
        welcomeMessage.put("message", "Welcome to Oásis Marítimo API!");
        return welcomeMessage;
    }
}