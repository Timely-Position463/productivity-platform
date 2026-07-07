package com.ajay.productivity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email should not be Empty")
        @Email(message = "Please enter email in a correct format, example: example@email.com")
        String email,
        @NotBlank(message = "Password should not be Empty")
        String password
) {
}
