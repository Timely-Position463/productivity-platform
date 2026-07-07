package com.ajay.productivity.exception;

import com.ajay.productivity.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request){
        var fieldErrors=e.getBindingResult().getFieldErrors();
        Map<String,String> errors=new HashMap<>();
        for(var error:fieldErrors){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Validation Failed",errors));
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> jobNotFoundHandler(JobNotFoundException e, HttpServletRequest request){
        HttpStatus status=HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> userNotFoundHandler(UserNotFoundException e, HttpServletRequest request){
        log.error("User not found {}", e.getMessage());
        HttpStatus status=HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> genericExceptionHandler(Exception e, HttpServletRequest request){
        log.error("Unexpected Error",e);
        HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiErrorResponse> invalidInputHandler(InvalidInputException e, HttpServletRequest request){
        log.error("Provided Input is not accepted");
        HttpStatus status=HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> invalidCredentialsHandler(BadCredentialsException e, HttpServletRequest request){
        log.error("Provided Wrong credentials");
        HttpStatus status=HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(buildErrorResponse(status,e.getMessage(),request));
    }

    public ApiErrorResponse buildErrorResponse(HttpStatus status, String message, HttpServletRequest request){
        return new ApiErrorResponse(Instant.now(),status.value(), status.getReasonPhrase(), message,request.getRequestURI());
    }

}
