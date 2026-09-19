package com.example.waqar.Exceptions;

import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CookieNotFoundException.class)
    public ResponseEntity<ErrorDto> CookieNotFoundException(CookieNotFoundException e) {
        System.out.println(e.getMessage());     //change to logging later
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("cookie: " + e.getCookieName() + " not found"));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDto> AuthException(AuthException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("some authentication/authoriztion error has occurred: "
                + e.getPublicReason()));
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorDto> ClientNotFoundException(ClientNotFoundException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("client not found, searched: "
                + e.getFieldOfSearch() + "for value: " + e.getSearchedValue()));
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorDto> InvalidJwtException(InvalidJwtException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("invalid JWT: " + e.getReason()));
    }
}
