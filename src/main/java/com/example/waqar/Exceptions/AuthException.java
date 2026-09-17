package com.example.waqar.Exceptions;

public class AuthException extends RuntimeException implements CustomWaqarException {
    public AuthException(String message) {
        super("AuthException: " + message);
    }
}
