package com.app.controller;

import com.app.entity.User;
import com.app.dto.UserResponseDTO;
import com.app.service.UserService;
// import com.app.security.TokenMaker;
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
    // private final TokenMaker tokenMaker;

    // @PostMapping
    // public ResponseEntity<User> createUser(@RequestBody User user) {
    //     try {
    //         User createdUser = userService.createUser(user);
    //         return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    //     } catch (Exception e) {
    //         // Handle exception and return a specific response
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody User user) {
        LOGGER.debug("Received request to create user: {}", user);
        try {
            User createdUser = userService.createUser(user);
            UserResponseDTO userResponseDTO = transform(Optional.ofNullable(createdUser));
            LOGGER.debug("User created successfully: {}", createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (Exception e) {
            LOGGER.error("Error creating user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        LOGGER.debug("Call loginUser API");
        try {
            // Attempt to login and generate a token
            String token = userService.loginUser(username, password); //, tokenMaker);
            return token;  // Return the token as a plain String
        } catch (Exception e) {
            // Handle invalid credentials or other errors
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", e);
        }
    }

    @GetMapping("/{username}")
    public UserResponseDTO getUser(@PathVariable String username) {
        LOGGER.debug("Call getUser API for user.");
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
        // Create a variable to hold the transformed DTO or null
        UserResponseDTO[] result = new UserResponseDTO[1];  // Using array to hold a reference
    
        // If the userOptional is present, transform it
        userOptional.ifPresentOrElse(user -> {
                result[0] = new UserResponseDTO.Builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .agency(user.getAgency())
                .appContact(user.getAppContact())
                .appContactEmail(user.getAppContactEmail())
                .passwordChangedAt(user.getPasswordChangedAt())
                .createdAt(user.getCreatedAt())
                .build();
        }, () -> {
            // No-op: If Optional is empty, result[0] will stay null
        });
    
        // Return the result, which will be null if no user was present
        return result[0];
    }
        
}
