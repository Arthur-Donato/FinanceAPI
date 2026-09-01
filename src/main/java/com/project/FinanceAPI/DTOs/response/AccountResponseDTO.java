package com.project.FinanceAPI.DTOs.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponseDTO(
        UUID accountId,
        String name,
        UUID userId,
        LocalDateTime created_at
) {
}
