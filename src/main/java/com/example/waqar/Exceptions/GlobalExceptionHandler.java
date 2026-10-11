package com.example.waqar.Exceptions;

import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.MiscDtos.ErrorMapDto;
import com.example.waqar.Services.LoggerService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("client not found, searched: "
                + e.getFieldOfSearch() + "for value: " + e.getSearchedValue()));
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorDto> InvalidJwtException(InvalidJwtException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("invalid JWT: " + e.getReason()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDto> ExpiredJwtException(ExpiredJwtException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> ClientAlreadyExistsException(ClientAlreadyExistsException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("the client with " + e.getField() + ": "
                + e.getValue() + " already exists"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMapDto> ConstraintViolationException(ConstraintViolationException e) {
        logError(e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
        return ResponseEntity.badRequest().body(new ErrorMapDto(errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //for when @Valid throws the exception
    public ResponseEntity<ErrorMapDto> handleBodyValidation(MethodArgumentNotValidException ex) {
        logError(ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ErrorMapDto(errors));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex){
        logError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorDto> PatientNotFoundException(PatientNotFoundException e) {
        logError(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Patient not found, searched: "
                + e.getFieldOfSearch() +  "for value: " + e.getSearchedValue()));
    }
}
