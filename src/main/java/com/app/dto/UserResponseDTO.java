package com.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private String username;
    private String fullName;
    private String email;
    private String agency;
    private String appContact;
    private String appContactEmail;
    private LocalDateTime passwordChangedAt;
    private LocalDateTime createdAt;
}
