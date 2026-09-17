package com.example.waqar.Exceptions;

public class InvalidJwtException extends RuntimeException implements CustomWaqarException {
    public InvalidJwtException(String reason) {
        super("invalidJwtException due to: " + reason);
    }
}
