package com.app.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
    String username,
    String fullName,
    String email,
    String agency,
    String appContact,
    String appContactEmail,
    LocalDateTime passwordChangedAt,
    LocalDateTime createdAt
) {
    
    // Static Builder class inside the record to implement Builder pattern
    public static class Builder {
        private String username;
        private String fullName;
        private String email;
        private String agency;
        private String appContact;
        private String appContactEmail;
        private LocalDateTime passwordChangedAt;
        private LocalDateTime createdAt;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder agency(String agency) {
            this.agency = agency;
            return this;
        }

        public Builder appContact(String appContact) {
            this.appContact = appContact;
            return this;
        }

        public Builder appContactEmail(String appContactEmail) {
            this.appContactEmail = appContactEmail;
            return this;
        }

        public Builder passwordChangedAt(LocalDateTime passwordChangedAt) {
            this.passwordChangedAt = passwordChangedAt;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserResponseDTO build() {
            return new UserResponseDTO(username, fullName, email, agency, appContact, appContactEmail, passwordChangedAt, createdAt);
        }
    }
}
