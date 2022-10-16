package com.example.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public ErrorFilter errorFilter() {
//        return new ErrorFilter();
//    }
//
//    @Bean
//    public PreFilter preFilter() {
//        return new PreFilter();
//    }
//
//    @Bean
//    public RouteFilter RouteFilter() {
//        return new RouteFilter();
//    }
//
//    @Bean
//    public PostFilter PostFilter() {
//        return new PostFilter();
//    }

}
