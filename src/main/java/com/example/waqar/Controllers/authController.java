package com.example.waqar.Controllers;

import com.example.waqar.Dtos.MiscDtos.JwtTokenRes;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Exceptions.ClientNotFoundException;
import com.example.waqar.Exceptions.CookieNotFoundException;
import com.example.waqar.Repositories.ClientRepo;
import com.example.waqar.Services.LoggerService;
import com.example.waqar.Services.SecurityServices.Jwt;
import com.example.waqar.Services.SecurityServices.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class authController {
    private JwtService jwtService;
    private final ClientRepo clientRepo;
    private LoggerService logger;

    @PostMapping("refresh")
    public ResponseEntity<? extends ResponseBodyDto> refresh(@CookieValue(value = "refreshToken", required = false) String token){
        if(token == null){
            throw new CookieNotFoundException("refreshToken");
        }
        Jwt refreshJwt = jwtService.parseToken(token);
        logger.log(refreshJwt.toString());
        refreshJwt.validateJwt();
        Client client = clientRepo.findById(refreshJwt.getClientId()).orElseThrow(() -> new ClientNotFoundException("id", refreshJwt.getClientId().toString()));
        String accessToken = jwtService.generateAccessToken(client).toString();
        logger.log("Access Token: " + accessToken);
        return ResponseEntity.ok(new JwtTokenRes(accessToken));
    }
}
