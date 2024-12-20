package com.app.dto;

public record LoginResponseDTO(
        UserResponseDTO user,
        String sessionId,
        String accessToken) {

    // Static Builder class
    public static class Builder {
        private UserResponseDTO user;
        private String sessionId;
        private String accessToken;

        // Builder method to set the 'user' field
        public Builder user(UserResponseDTO user) {
            this.user = user;
            return this;
        }

        // Builder method to set the 'sessionId' field
        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        // Builder method to set the 'accessToken' field
        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        // Build method to return the final instance of LoginResponseDTO
        public LoginResponseDTO build() {
            return new LoginResponseDTO(user, sessionId, accessToken);
        }
    }
}
