package com.project.FinanceAPI.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
        @NotNull
        @NotBlank
        String name
) {
}
