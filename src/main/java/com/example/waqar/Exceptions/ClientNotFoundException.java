package com.example.waqar.Exceptions;

import lombok.Getter;

@Getter
public class ClientNotFoundException extends RuntimeException implements CustomWaqarException {
    private final String fieldOfSearch;
    private final String searchedValue;
    public ClientNotFoundException(String fieldOfSearch, String searchedValue) {
        super("user with " + fieldOfSearch + ": " + searchedValue + " not found");
        this.fieldOfSearch = fieldOfSearch;
        this.searchedValue = searchedValue;
    }
}
