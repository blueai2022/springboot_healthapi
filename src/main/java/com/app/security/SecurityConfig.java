package com.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Bean for AuthenticationManager (this is now explicitly defined)
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        return authenticationManagerBuilder.build();
    }

    // Configure HTTP security (URL protection, CSRF, etc.)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/api/v1/users/login", "/api/v1/users/register").permitAll() // Correct path for login and register
            .antMatchers("/api/v1/users", "/api/v1/users/{username}").permitAll() // Allow public access to the createUser endpoint
            .anyRequest().authenticated() // Require authentication for all other requests
            .and()
            .csrf().disable(); // Disable CSRF for now (may need re-enabling depending on your authentication method)

        return http.build();
    }
    
}
