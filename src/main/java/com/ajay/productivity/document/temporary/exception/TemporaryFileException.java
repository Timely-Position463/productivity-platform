package com.ajay.productivity.document.temporary.exception;

import java.io.IOException;

public class TemporaryFileException extends RuntimeException {
    public TemporaryFileException(String message, Throwable cause) {
        super(message,cause);
    }
}
