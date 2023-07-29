package com.example.config;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SecurityConfiguration {

    @GetMapping("/h")
    public String h1(HttpSession session){
        Object springSecurityContext = session.getAttribute("SPRING SECURITY_CONTEXT");
        return "Hello World";
    }
}
