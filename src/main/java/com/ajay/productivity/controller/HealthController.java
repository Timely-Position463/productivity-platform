package com.ajay.productivity.controller;

import com.ajay.productivity.dto.HealthResponse;
import com.ajay.productivity.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;


    @GetMapping("/health")
    public HealthResponse getHealth(){
        return healthService.getHealth();
    }

}
