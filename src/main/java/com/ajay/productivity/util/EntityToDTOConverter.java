package com.ajay.productivity.util;

import com.ajay.productivity.dto.JobResponse;
import com.ajay.productivity.dto.UserResponse;
import com.ajay.productivity.entity.User;
import com.ajay.productivity.entity.UtilityJob;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTOConverter {
    public UserResponse userToUserResponse(User user){
        return new UserResponse(user.getId(),user.getUsername(), user.getEmail());
    }

    public JobResponse jobToJobResponse(UtilityJob job){
        return new JobResponse(job.getId(),job.getFilename(),job.getStatus());
    }
}
