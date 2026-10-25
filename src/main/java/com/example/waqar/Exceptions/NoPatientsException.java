package com.example.waqar.Exceptions;

public class NoPatientsException extends RuntimeException implements CustomWaqarException{
    public NoPatientsException(String client) {super("no patients found for client: " + client);}
}
