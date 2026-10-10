package com.example.waqar.Services;

import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    public void log(String message) {
        System.out.println("logger: " + message); //TODO: turn into a real logger
    }
}
