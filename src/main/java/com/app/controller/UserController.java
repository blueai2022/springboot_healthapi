package com.app.controller;

import com.app.entity.User;
import com.app.dto.UserResponseDTO;
import com.app.service.UserService;
import com.app.security.TokenMaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final TokenMaker tokenMaker;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            // Handle exception and return a specific response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        LOGGER.debug("Call loginUser API");
        try {
            // Attempt to login and generate a token
            String token = userService.loginUser(username, password, tokenMaker);
            return token;  // Return the token as a plain String
        } catch (Exception e) {
            // Handle invalid credentials or other errors
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", e);
        }
    }

    @GetMapping("/users/{username}")
    public UserResponseDTO getUser(@PathVariable String username) {
        LOGGER.debug("Call getUser API for user: {}", username);
        try {
            // Retrieve the user based on username
            Optional<User> user = userService.getUser(username);
            UserResponseDTO userResponseDTO = transform(user);
            return userResponseDTO;  // Return the user object directly
        } catch (Exception e) {
            // Handle user not found or other errors
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    public UserResponseDTO transform(Optional<User> userOptional) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setFullName(user.getFullName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAgency(user.getAgency());
            userResponseDTO.setAppContact(user.getAppContact());
            userResponseDTO.setAppContactEmail(user.getAppContactEmail());
            userResponseDTO.setPasswordChangedAt(user.getPasswordChangedAt());
            userResponseDTO.setCreatedAt(user.getCreatedAt());
            return userResponseDTO;
        } else {
            return null;  // or throw an exception if needed
        }
    }
}
