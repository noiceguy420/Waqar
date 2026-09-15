package com.example.waqar.Services.SecurityServices;

import com.example.waqar.Entities.Client;
import com.example.waqar.Exceptions.AuthException;
import com.example.waqar.Exceptions.ClientNotFoundException;
import com.example.waqar.Repositories.ClientRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ClientRepo clientRepo;

    public AuthService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client getLoggedInClient(){
        try {
            Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return clientRepo.findById(id).orElseThrow(() -> new ClientNotFoundException("ID", id.toString()));
        }
        catch (NullPointerException e){
            throw new AuthException("id was null");
        }
    }
}
