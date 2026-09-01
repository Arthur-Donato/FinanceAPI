package com.project.FinanceAPI.DTOs.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID userId,
        String name,
        String email,
        LocalDateTime created_at
) {
}
