package com.example.waqar.Services.SecurityServices;

import com.example.waqar.Dtos.ClientDtos.ClientLoginReq;
import com.example.waqar.Entities.Client;
import com.example.waqar.Exceptions.AuthException;
import com.example.waqar.Exceptions.ClientNotFoundException;
import com.example.waqar.Repositories.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final ClientRepo clientRepo;
    private AuthenticationManager authenticationManager;

    public Client getLoggedInClient(){
        try {
            Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return clientRepo.findById(id).orElseThrow(() -> new ClientNotFoundException("ID", id.toString()));
        }
        catch (NullPointerException e){
            throw new AuthException("id was null", "no id provided");
        }
    }

    public Client LoginClientHelper(ClientLoginReq req){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getIdentifier(), req.getPassword()));
            return clientRepo.findByUsername(req.getIdentifier())
                    .or(() -> clientRepo.findByEmail(req.getIdentifier()))
                    .orElseThrow(() -> new ClientNotFoundException("email or username", req.getIdentifier()));

    }
}
