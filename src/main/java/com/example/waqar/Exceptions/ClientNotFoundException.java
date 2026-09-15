package com.example.waqar.Exceptions;

public class ClientNotFoundException extends RuntimeException implements CustomWaqarException {
    public ClientNotFoundException(String fieldOfSearch, String searchValue) {
        super("user with " + fieldOfSearch + ": " + searchValue + " not found");
    }
}
