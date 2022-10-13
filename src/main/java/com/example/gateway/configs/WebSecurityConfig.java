package com.example.gateway.configs;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class WebSecurityConfig  {

    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().ignoringAntMatchers("/eureka/**");
	http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
	http.formLogin();
	http.httpBasic();
    }

}