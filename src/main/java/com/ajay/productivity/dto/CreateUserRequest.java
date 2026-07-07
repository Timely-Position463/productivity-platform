package com.ajay.productivity.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateUserRequest(
        @NotBlank(message = "The username should not be empty")
        @Size(min = 3, max = 50, message = "The length of Username should be between 3 and 50")
        String username,
        @NotBlank(message = "The email is a necessary field")
        @Email
        String email,
        @NotBlank(message = "Password is a necessary field")
        @Size(min = 8, message = "The length of the Password must be at least 8 characters")
        String password
) {
}
