package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.gateway.filters.PreFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
	SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public PreFilter preFilter() {
	return new PreFilter();
    }

}
