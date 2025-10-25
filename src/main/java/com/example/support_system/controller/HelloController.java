package com.example.support_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Добро пожаловать в систему техподдержки!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Привет, Spring Boot!";
    }

    @GetMapping("/info")
    public String info() {
        return "Это тестовое приложение для задания 1";
    }
}