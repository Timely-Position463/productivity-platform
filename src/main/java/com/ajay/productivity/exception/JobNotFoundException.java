package com.ajay.productivity.exception;

import com.ajay.productivity.model.Status;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long id) {
        super("Job Not Found with the id: "+id);
    }

    public JobNotFoundException(Status status){
        super("Job Not found with the status: "+status+"\nTry any of these: PENDING, SUCCESS, FAILURE");
    }
}
