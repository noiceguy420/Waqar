package com.example.waqar.Exceptions;

import com.example.waqar.Dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> CookieNotFoundException(CookieNotFoundException e) {
        System.out.println(e.getMessage());     //change to logging later
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("cookie: " + e.getCookieName() + " not found"));
    }
}
