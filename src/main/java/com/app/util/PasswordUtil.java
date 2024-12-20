package com.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Utility method to encrypt/hash the password
    public static String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);  // Hash the raw password using BCrypt
    }

    // Utility method to check if the raw password matches the hashed password
    public static boolean checkPasswordMatch(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);  // Compare raw password with hashed password
    }
}
