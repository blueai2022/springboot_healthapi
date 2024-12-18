package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String agent;
    private String fullName;
    private Long addressID;
    private Date birthDate;
    private String driverLicenseNumber;
    private String driverLicenseState;
    private String email;
    private Timestamp createdAt;
}
