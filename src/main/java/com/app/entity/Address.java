package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressLine1; 
    private String addressLine2; 

    private String city; 
    private String state; 
    private String zipCode; 
    private String country; 

    private LocalDateTime createdAt; 

    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
