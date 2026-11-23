package com.banking.banking_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @GetMapping()
    public String hello() {
        return "Banking System API is running!!!";
    }
}
