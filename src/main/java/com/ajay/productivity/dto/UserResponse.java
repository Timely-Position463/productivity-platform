package com.ajay.productivity.dto;

import java.util.List;

public record UserResponse(
        Long id,
        String username,
        String email,
        List<JobResponse> jobs

) {
}
