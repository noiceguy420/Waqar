package com.example.waqar.Exceptions;

public class BadRequestException extends RuntimeException implements CustomWaqarException{
    public BadRequestException(String message) {
        super(message);
    }
}
