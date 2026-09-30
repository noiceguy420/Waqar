package com.example.waqar.Exceptions;

import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Services.LoggerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private LoggerService logger;

    private void logError(String message) {
        logger.log(message); //TODO:is it better to replace each logError function call or leave as is?
    }

    @ExceptionHandler(CookieNotFoundException.class)
    public ResponseEntity<ErrorDto> CookieNotFoundException(CookieNotFoundException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("cookie: "
                + e.getCookieName() + " not found"));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDto> AuthException(AuthException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(
                "some authentication/authoriztion error has occurred: " + e.getPublicReason()));
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorDto> ClientNotFoundException(ClientNotFoundException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("client not found, searched: "
                + e.getFieldOfSearch() + "for value: " + e.getSearchedValue()));
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorDto> InvalidJwtException(InvalidJwtException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("invalid JWT: " + e.getReason()));
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> ClientAlreadyExistsException(ClientAlreadyExistsException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("the client with " + e.getField() + ": "
                + e.getValue() + " already exists"));
    }
}
