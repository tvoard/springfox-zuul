package com.example.api.apple.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class AppleController {

    @GetMapping(path = "/")
    public String base() {
        String services = "Service-Apple has been started";
        System.out.println(services);
        return services;
    }

}