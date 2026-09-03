package com.cp.airsync.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
class HelloWorld{
    
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World! Welcome to Airsync!";
    }
}