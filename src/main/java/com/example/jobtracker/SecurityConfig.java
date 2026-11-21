package com.example.jobtracker;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(   // all the rules will start from here
                auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/applications/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())    // httpBasic: Enables HTTP Basic Authentication.
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
