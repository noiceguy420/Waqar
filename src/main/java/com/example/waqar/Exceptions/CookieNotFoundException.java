package com.example.waqar.Exceptions;

import lombok.Getter;

@Getter
public class CookieNotFoundException extends AuthException implements CustomWaqarException{
    private final String cookieName;
    public CookieNotFoundException(String cookieName) {
        super("CookieNotFoundException: can't find cookie: " + cookieName,
                "CookieNotFoundException: can't find cookie: " + cookieName);
        this.cookieName = cookieName;
    }
}
