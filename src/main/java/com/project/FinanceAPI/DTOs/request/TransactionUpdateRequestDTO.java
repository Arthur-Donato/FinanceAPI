package com.project.FinanceAPI.DTOs.request;

import com.project.FinanceAPI.model.enums.TransactionsType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionUpdateRequestDTO(
        TransactionsType type,

        @Size(max=255)
        String description,

        @PastOrPresent
        LocalDate date,

        @DecimalMin(value = "0.01")
        BigDecimal value,

        UUID categoryId
) {
}
