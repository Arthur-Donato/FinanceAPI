package com.project.FinanceAPI.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime dateTime,
        int status,
        String error,
        String message,
        String path
) {
}
