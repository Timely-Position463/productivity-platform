package com.ajay.productivity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Schema(
                description = "User email address",
                example = "ajay@example.com"
        )
        @NotBlank(message = "Email should not be Empty")
        @Email(message = "Please enter email in a correct format, example: example@email.com")
        String email,
        @NotBlank(message = "Password should not be Empty")
        @Schema(
                description = "User password",
                example = "StrongPassword@123"
        )
        String password
) {
}
