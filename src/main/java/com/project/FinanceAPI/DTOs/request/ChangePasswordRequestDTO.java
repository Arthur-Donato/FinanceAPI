package com.project.FinanceAPI.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequestDTO(

        @NotBlank
        String currentPassword,

        @NotBlank
        @Size(min = 8, message = "The password has to be at least 8 characters.")
        String newPassword
) {
}
