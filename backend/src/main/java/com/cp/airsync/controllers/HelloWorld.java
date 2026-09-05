package com.cp.airsync.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
class HelloWorld{
    
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World! Welcome to Airsync!";
    }
}