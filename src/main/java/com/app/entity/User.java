package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Transient
    private String password; 

    @JsonIgnore
    private String hashedPassword;
    private String fullName;
    private String email;
    private Long addressID;
    private String agency;
    private String appContact;
    private String appContactEmail;
    private LocalDateTime passwordChangedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void updatePasswordChangedAt() {
        if (this.hashedPassword != null && !this.hashedPassword.isBlank()) {
            this.passwordChangedAt = LocalDateTime.now(); 
        }
    }
}
