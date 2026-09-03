package com.project.FinanceAPI.DTOs.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
