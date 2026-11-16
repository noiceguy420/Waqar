package com.example.waqar.Exceptions;

public class ClientPatientMismatchException extends AuthException {
    public ClientPatientMismatchException() {
        super("client doesn't own patient", "this patient is not yours");
    }
}
