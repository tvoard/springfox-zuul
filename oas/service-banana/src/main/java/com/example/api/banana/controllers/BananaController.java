package com.example.api.banana.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class BananaController {

    @GetMapping(path = "/")
    public String base() {
        String services = "Service-Banana has been started";
        System.out.println(services);
        return services;
    }

}
