package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;

    public String toJson() {
        return "{\"message\": \"" + message + "\"}";
    }
}
