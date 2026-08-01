package com.learning.todo.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String accessToken,
        String tokenType,
        Long expiresIn
) {
}
