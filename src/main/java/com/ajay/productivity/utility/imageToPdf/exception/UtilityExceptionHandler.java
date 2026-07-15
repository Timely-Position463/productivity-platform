package com.ajay.productivity.utility.imageToPdf.exception;

import com.ajay.productivity.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class UtilityExceptionHandler {
    private final com.ajay.productivity.exception.GlobalExceptionHandler handler;

    @ExceptionHandler(ImageValidationException.class)
    public ResponseEntity<ApiErrorResponse> ImageValidationExceptionHandler(ImageValidationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status, e.getMessage(), request));
    }

    @ExceptionHandler(PdfGenerationException.class)
    public ResponseEntity<ApiErrorResponse> PdfGenerationExceptionHandler(PdfGenerationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status, e.getMessage(), request));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiErrorResponse> maxUploadSizeExceptionHandler(MaxUploadSizeExceededException e, HttpServletRequest request){
        HttpStatus status=HttpStatus.PAYLOAD_TOO_LARGE;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiErrorResponse> missingServletRequestPartExceptionHandler(MissingServletRequestPartException e,HttpServletRequest request){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiErrorResponse> multipartExceptionHandler(MultipartException e,HttpServletRequest request){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e,HttpServletRequest request){
        HttpStatus status=HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status,e.getMessage(),request));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e,HttpServletRequest request){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(handler.buildErrorResponse(status,e.getMessage(),request));
    }

}