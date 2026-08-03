package com.ajay.productivity.controller;

import com.ajay.productivity.dto.HealthResponse;
import com.ajay.productivity.service.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Health",
        description = "Application health endpoints."
)
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    @Operation(
            summary = "Application health",
            description = "Returns the current health status of the application."
    )
    @GetMapping("/health")
    public HealthResponse getHealth(){
        return healthService.getHealth();
    }

}
