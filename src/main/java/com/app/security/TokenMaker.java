package com.app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;

@Component
public class TokenMaker {

    @Value("${app.token.secret}")
    private String secretKey;

    // Token expiration duration, e.g. 1 hour
    @Value("${app.token.expiration}")
    private long expirationTime;

    public String createToken(String username) {
        Date expirationDate = Date.from(Instant.now().plusSeconds(expirationTime));

        // Use a library like JWT or PASETO to generate the token
        // Example: generate JWT token (pseudo code)
        return "generated-token"; // Replace with actual token generation logic
    }

    public boolean validateToken(String token) {
        // Validate the token
        // Example: JWT validation (pseudo code)
        return true; // Replace with actual validation logic
    }
}
