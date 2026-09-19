package com.example.waqar.Controllers;

import com.example.waqar.Configs.JwtConfig;
import com.example.waqar.Dtos.*;
import com.example.waqar.Dtos.ClientDtos.ClientLoginReq;
import com.example.waqar.Dtos.ClientDtos.NewClientReqDto;
import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.MiscDtos.JwtTokenRes;
import com.example.waqar.Entities.Client;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Services.ClientService;
import com.example.waqar.Services.SecurityServices.AuthService;
import com.example.waqar.Services.SecurityServices.Jwt;
import com.example.waqar.Services.SecurityServices.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final AuthService authService;
    private JwtService jwtService;
    private JwtConfig jwtConfig;

    @PostMapping("new")
    public ResponseEntity<? extends CustomWaqarDto> newClient(@RequestBody @Valid NewClientReqDto req, UriComponentsBuilder uriBuilder) {
        CustomWaqarDto res = clientService.newClientHelper(req);

        if(res instanceof ErrorDto)
            return ResponseEntity.badRequest().body(res);

        URI uri = uriBuilder.path("/me").buildAndExpand(res).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @PostMapping("login")
    public ResponseEntity<? extends CustomWaqarDto> loginClient(@RequestBody @Valid ClientLoginReq req, HttpServletResponse response){
        System.out.println("entered loginClient");
        Client client = authService.LoginClientHelper(req);
        System.out.println("client = "+client);
        if(client == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new ErrorDto("invalid credentials"));
        Jwt accessToken = jwtService.generateAccessToken(client);
        Jwt refreshToken = jwtService.generateRefreshToken(client);

        var cookie = new Cookie("refreshToken", refreshToken.toString());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(jwtConfig.getRefreshExpiration());
        cookie.setSecure(true);
        cookie.setPath("/auth/refresh");
        response.addCookie(cookie);
        return ResponseEntity.ok(new JwtTokenRes(accessToken.toString()));
    }



    @GetMapping("me")
    public ResponseEntity<? extends CustomWaqarDto> me() {
        return ResponseEntity.ok(clientMapper.toDto(authService.getLoggedInClient()));
    }

}
