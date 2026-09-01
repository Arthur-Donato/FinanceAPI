package com.project.FinanceAPI.DTOs.response;

import com.project.FinanceAPI.model.enums.TransactionsType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID transactionId,
        TransactionsType type,
        String description,
        LocalDate date,
        BigDecimal value,
        UUID accountId,
        UUID categoryId,
        LocalDateTime created_at
) {
}
