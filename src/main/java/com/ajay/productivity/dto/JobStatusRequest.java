package com.ajay.productivity.dto;

import com.ajay.productivity.model.Status;
import jakarta.validation.constraints.NotNull;

public record JobStatusRequest(
        @NotNull Status status
        ) {
}
