package com.example.waqar.Exceptions;

import lombok.Getter;

@Getter
public class PatientNotFoundException extends RuntimeException implements CustomWaqarException{
    private final String fieldOfSearch;
    private final String searchedValue;
    public PatientNotFoundException(String fieldOfSearch, String searchedValue) {
        super("patient with " + fieldOfSearch + ": " + searchedValue + " not found");
        this.fieldOfSearch = fieldOfSearch;
        this.searchedValue = searchedValue;
    }
}
