package com.ajay.productivity.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User not found with the provided id: "+id);
    }

    public UserNotFoundException(String email) {
        super("User not found with the provided email: "+email);
    }
}
