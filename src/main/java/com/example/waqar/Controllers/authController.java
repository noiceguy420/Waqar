package com.example.waqar.Controllers;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.MiscDtos.JwtTokenRes;
import com.example.waqar.Entities.Client;
import com.example.waqar.Exceptions.ClientNotFoundException;
import com.example.waqar.Exceptions.CookieNotFoundException;
import com.example.waqar.Repositories.ClientRepo;
import com.example.waqar.Services.SecurityServices.Jwt;
import com.example.waqar.Services.SecurityServices.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class authController {
    private JwtService jwtService;
    private final ClientRepo clientRepo;

    @PostMapping("refresh")
    public ResponseEntity<? extends CustomWaqarDto> refresh(@CookieValue(value = "refreshToken", required = false) String token){
        if(token == null){
            throw new CookieNotFoundException("refreshToken");
        }
        Jwt refreshJwt = jwtService.parseToken(token);
        System.out.println(refreshJwt);
        if(refreshJwt.isInvalid())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Token is invalid (might be expired, or empty)"));
        Client client = clientRepo.findById(refreshJwt.getClientId()).orElseThrow(() -> new ClientNotFoundException("id", refreshJwt.getClientId().toString()));
        String accessToken = jwtService.generateAccessToken(client).toString();
        System.out.println("Access Token: " + accessToken);
        return ResponseEntity.ok(new JwtTokenRes(accessToken));
    }
}
