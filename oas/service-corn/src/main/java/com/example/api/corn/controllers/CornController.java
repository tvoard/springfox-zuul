package com.example.api.corn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class CornController {

    @GetMapping(path = "/")
    public String base() {
        String services = "Service-Corn has been started";
        System.out.println(services);
        return services;
    }

}
