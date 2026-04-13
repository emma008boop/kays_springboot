package com.myapp.gestor.dto.auth;

import lombok.Builder;

@Builder
public record RegisterUserResponse(String username, String email) {
}
