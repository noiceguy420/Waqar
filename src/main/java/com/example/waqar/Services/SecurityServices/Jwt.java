package com.example.waqar.Services.SecurityServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;

    public Jwt(Claims claims, SecretKey secretKey) {
        this.claims = claims;
        this.secretKey = secretKey;
    }

    public boolean isValid() {
        return !isExpired(); //add any other conditions here
    }
    private boolean isExpired() {return claims.getExpiration().after(new Date());}

    public Integer getClientId() {return Integer.parseInt(claims.getSubject());}

    @Override
    public String toString() {return Jwts.builder().claims(claims).signWith(secretKey).compact();}
}
