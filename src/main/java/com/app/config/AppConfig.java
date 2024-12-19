package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Define the UserDetailsService bean
    @Bean
    public UserDetailsService userDetailsService() {
        // Define a dummy user for illustration purposes
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password")  // {noop} is a password encoder that does not encode the password
                .roles("USER")
                .build();

        // You can create an InMemoryUserDetailsManager with the user (you can also load from a DB or other source)
        return new InMemoryUserDetailsManager(user);
    }
}
