package com.project.FinanceAPI.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(

        @Size(min = 2, max = 255)
        String name,

        @Email
        String email
) {
}
