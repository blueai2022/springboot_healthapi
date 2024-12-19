package com.app.service;

import com.app.entity.User;
// import com.app.security.TokenMaker;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        // You can add validation for unique usernames or emails before saving
        User savedUser = userRepository.save(user);
        return getUser(savedUser.getUsername()).orElse(null);
    }

    public String loginUser(String username, String password) {
        // Validate username and password (this would likely involve hashing the password)
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return "TEMP_TOKEN"; //tokenMaker.createToken(username);
        } else {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    // Delete a user by username
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    // Check if a user exists by username
    public boolean checkUserExistence(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
