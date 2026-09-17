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

    public boolean isInvalid() {

        return isEmpty() || isExpired(); //add any other conditions here
    }
    private boolean isExpired() {return claims.getExpiration().before(new Date());}
    private boolean isEmpty() {return (claims == null && secretKey == null);}


    public Integer getClientId() {
        try {
            return Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            if(isEmpty())
                throw new InvalidJwtException("JWT is empty");
            if(isExpired())
                throw new InvalidJwtException("JWT is expired");
        }
        return null;
    }

    @Override
    public String toString() {return Jwts.builder().claims(claims).signWith(secretKey).compact();}
}
