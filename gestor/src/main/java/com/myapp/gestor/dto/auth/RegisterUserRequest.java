package com.myapp.gestor.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterUserRequest(@NotBlank(message = "User must have a username") String username,
        @NotBlank(message = "user's email is required") @Email(message = "Email format only") String email,
        @NotBlank(message = "password is required") String passwordHash) {
}
