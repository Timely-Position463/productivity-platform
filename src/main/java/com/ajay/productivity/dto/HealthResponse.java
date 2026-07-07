package com.ajay.productivity.dto;

import com.ajay.productivity.model.Status;

public record HealthResponse(
        Status status, String application
) {
}
