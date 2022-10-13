package com.example.gateway.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/")
    public String home() {
	return "Hello world";
    }

    @GetMapping("/a")
    public String findById(HttpServletRequest req, HttpServletResponse res) {
	return "";
    }

}
