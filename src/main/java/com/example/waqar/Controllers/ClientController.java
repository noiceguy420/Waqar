package com.example.waqar.Controllers;

import com.example.waqar.Dtos.*;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Services.ClientService;
import com.example.waqar.Services.SecurityServices.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping("new")
    public ResponseEntity<? extends CustomWaqarDto> newClient(@RequestBody @Valid NewClientReqDto req, UriComponentsBuilder uriBuilder) {
        CustomWaqarDto res = clientService.newClientHelper(req);

        if(res instanceof ErrorDto)
            return ResponseEntity.badRequest().body(res);

        URI uri = uriBuilder.path("/me").buildAndExpand(res).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping("me")
    public ResponseEntity<? extends CustomWaqarDto> me() {
        return ResponseEntity.ok(clientMapper.toDto(authService.getLoggedInClient()));
    }

}
