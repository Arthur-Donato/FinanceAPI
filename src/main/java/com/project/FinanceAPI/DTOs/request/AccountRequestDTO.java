package com.project.FinanceAPI.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRequestDTO(
        @NotBlank
        String name
) {
}
