package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Transient // This will prevent the password field from being persisted by JPA
    private String password; 

    private String hashedPassword;
    private String fullName;
    private String email;
    private Long addressID;
    private String agency;
    private String appContact;
    private String appContactEmail;
    private LocalDateTime passwordChangedAt;
    private LocalDateTime createdAt;
}
