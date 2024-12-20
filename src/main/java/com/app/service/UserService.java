package com.app.service;

import com.app.dto.LoginResponseDTO;
import com.app.dto.UserResponseDTO;
import com.app.entity.User;
// import com.app.security.TokenMaker;
import com.app.repository.UserRepository;
import com.app.util.PasswordUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        if (checkUserExistence(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        String hashedPassword = PasswordUtil.encryptPassword(user.getPassword());
        user.setHashedPassword(hashedPassword);

        // You can add validation for unique usernames or emails before saving
        User savedUser = userRepository.save(user);
        return getUser(savedUser.getUsername()).orElse(null);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        // Validate username and password (this would likely involve hashing the password)
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && PasswordUtil.checkPasswordMatch(password, userOptional.get().getHashedPassword()) ) {
            User user = userOptional.get();
            UserResponseDTO userResponseDTO = new UserResponseDTO.Builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .agency(user.getAgency())
                .appContact(user.getAppContact())
                .appContactEmail(user.getAppContactEmail())
                .createdAt(user.getCreatedAt())
                .build();
            
            LoginResponseDTO response = new LoginResponseDTO.Builder()
                .user(userResponseDTO)  // Set the user
                .sessionId("generated-session-id")  // Set the sessionId
                .accessToken("generated-jwt-token")  // Set the access token
                .build();  // Create the final object

            
            return response; //tokenMaker.createToken(username);
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

    public User updateUser(String username, User updatedUser) {
        Optional<User> existingUserOptional = getUser(username);

        if (existingUserOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User existingUser = existingUserOptional.get();

        // Compare hashed passwords if the password has changed
        boolean passwordChanged = false;

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String newHashedPassword = PasswordUtil.encryptPassword(updatedUser.getPassword());

            // Check if the password is different from the existing one
            if (!newHashedPassword.equals(existingUser.getHashedPassword())) {
                existingUser.setHashedPassword(newHashedPassword);
                passwordChanged = true;
            }
        }

        // If the password has changed, update the passwordChangedAt field
        if (passwordChanged) {
            existingUser.setPasswordChangedAt(LocalDateTime.now());
        }

        // Copy other fields from updatedUser to existingUser if needed
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());

        // Save the updated user back to the database
        User savedUser = userRepository.save(existingUser);
        return savedUser;
    }

}
