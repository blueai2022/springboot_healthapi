package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String refreshToken;
    private String userAgent;
    private String clientIp;
    private boolean isBlocked;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
