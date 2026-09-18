package com.example.waqar.Exceptions;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException implements CustomWaqarException {

    private final String publicReason;
    public AuthException(String reason, String publicReason) {
        super("AuthException: " + reason);
        this.publicReason = publicReason;
    }
}
