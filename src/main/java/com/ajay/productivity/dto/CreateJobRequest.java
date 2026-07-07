package com.ajay.productivity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateJobRequest(
        @NotBlank(message = "file name must not be blank")
        @Size(min = 3,max = 255, message = "The file name should be within 3 to 255 characters long")
       String filename
) {
}
