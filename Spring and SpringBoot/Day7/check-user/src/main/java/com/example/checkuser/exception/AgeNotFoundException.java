package com.example.checkuser.exception;

public class AgeNotFoundException extends RuntimeException {
    public AgeNotFoundException(String message) {
        super(message);
    }
}