package com.example.waqar.Exceptions;

import lombok.Getter;

@Getter
public class ClientAlreadyExistsException extends RuntimeException {
    private final String value;
    private final String field; //email or username
    public ClientAlreadyExistsException(String field, String value) {
        super("the client with " + field + ": " + value + " already exists");
        this.field = field;
        this.value = value;
    }
}
