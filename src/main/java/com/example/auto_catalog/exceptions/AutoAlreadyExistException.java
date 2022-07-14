package com.example.auto_catalog.exceptions;

public class AutoAlreadyExistException extends RuntimeException {
    public AutoAlreadyExistException(String message) {
        super(message);
    }
}
