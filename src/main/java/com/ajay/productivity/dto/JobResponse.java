package com.ajay.productivity.dto;

import com.ajay.productivity.model.Status;

public record JobResponse(
        Long id, String filename, Status status
) {
}
