package com.example.waqar.Exceptions;

import lombok.Getter;

@Getter
public class InvalidJwtException extends RuntimeException implements CustomWaqarException {
    private final String reason;

    public InvalidJwtException(String reason) {
        super("invalidJwtException due to: " + reason);
        this.reason = reason;
    }
}
