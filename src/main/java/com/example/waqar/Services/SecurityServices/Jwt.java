package com.example.waqar.Services.SecurityServices;

import com.example.waqar.Exceptions.InvalidJwtException;
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
    public Jwt(){
        this.claims = null;
        this.secretKey = null;
    }

    public void validateJwt() {
        if(isEmpty())
            throw new InvalidJwtException("JWT is invalid");
        if(isExpired())
            throw new InvalidJwtException("JWT is expired");
    }
    private boolean isExpired() {return claims.getExpiration().before(new Date());}
    private boolean isEmpty() {return (claims == null && secretKey == null);}


    public Integer getClientId() {
        this.validateJwt();
        return Integer.parseInt(claims.getSubject());
    }

    @Override
    public String toString() {return Jwts.builder().claims(claims).signWith(secretKey).compact();}
}
