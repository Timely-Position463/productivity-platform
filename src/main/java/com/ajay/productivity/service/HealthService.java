package com.ajay.productivity.service;

import com.ajay.productivity.dto.HealthResponse;
import com.ajay.productivity.model.Status;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    public HealthResponse getHealth(){
        return new HealthResponse(Status.UP,"Productivity Platform");
    }
}
