package com.example.waqar.Services.SecurityServices;

import com.example.waqar.Configs.JwtConfig;
import com.example.waqar.Entities.Client;
import com.example.waqar.Exceptions.InvalidJwtException;
import com.example.waqar.Services.LoggerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;
    private LoggerService logger;


    public Jwt generateAccessToken(Client client){return generateToken(client, jwtConfig.getAccessExpiration());}
    public Jwt generateRefreshToken(Client client){return generateToken(client, jwtConfig.getRefreshExpiration());}
    private Jwt generateToken(Client client, long expiration) {
        logger.log("Generating JWT Token, current system time: " + new Date(System.currentTimeMillis()) + ", expirationVal: " + expiration);
        long newTime =  System.currentTimeMillis() + expiration;
        logger.log("New JWT Token expiration: " + new Date(newTime));

        Claims claims = Jwts.claims()
                .subject(client.getId().toString())
                .add("username", client.getUsername())
                .add("email", client.getEmail())
                .add("client phone number", client.getPhoneNumber())
                .issuedAt(new Date())
                .expiration(new Date(newTime)).build();
        return new Jwt(claims, jwtConfig.getSecretKey());
    }

    public Jwt parseToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        }
        catch (ExpiredJwtException e){
            throw new InvalidJwtException("Expired JWT Token");
        }

    }
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey()).build()
                .parseSignedClaims(token).getPayload();
    }


}
