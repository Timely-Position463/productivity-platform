package com.ajay.productivity.dto;


public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
