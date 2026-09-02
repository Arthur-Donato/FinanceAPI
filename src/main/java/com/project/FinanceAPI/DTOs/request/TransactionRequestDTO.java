package com.project.FinanceAPI.DTOs.request;

import com.project.FinanceAPI.model.enums.TransactionsType;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionRequestDTO(

        @NotBlank
        TransactionsType type,

        @Size(max = 255)
        String description,

        @NotNull
        @PastOrPresent
        LocalDate date,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal value,

        @NotNull
        UUID categoryId,

        @NotNull
        UUID accountId
) {
}
