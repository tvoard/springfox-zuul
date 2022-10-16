package com.example.api.gateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class GatewayController {

    @GetMapping(path = "/")
    public String home() {
        return "home";
    }

}
