package com.example.waqar.Exceptions;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super("AuthException: " + message);
    }
}
