package com.app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPayload {

    private UUID id;
    private String username;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;
    
}
