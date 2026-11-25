package com.example.waqar.Services;

import com.example.waqar.Dtos.ClientDtos.EditClientDto;
import com.example.waqar.Dtos.ClientDtos.NewClientReqDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Exceptions.AuthException;
import com.example.waqar.Exceptions.BadRequestException;
import com.example.waqar.Exceptions.ClientAlreadyExistsException;
import com.example.waqar.Exceptions.ClientNotFoundException;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Repositories.ClientRepo;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@AllArgsConstructor
public class ClientService implements UserDetailsService {
    private final ClientRepo clientRepo;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private LoggerService logger;

    @SuppressWarnings("NullableProblems")
    @Override
    public UserDetails loadUserByUsername(@NonNull String identifier) throws UsernameNotFoundException {
        Client client;
        if(identifier.contains("@"))
            client = clientRepo.findByEmail(identifier).orElseThrow(() -> new ClientNotFoundException("email", identifier));
        else
            client = clientRepo.findByUsername(identifier).orElseThrow(() -> new ClientNotFoundException("username", identifier));
        return new User(client.getUsername(), client.getPassHash(), Collections.emptyList());
    }

    public ResponseBodyDto newClientHelper(NewClientReqDto req){
        this.validateClientDetails(req.email(), req.phoneNumber(), req.username());

        if(clientRepo.existsByEmail(req.email()))
            throw new ClientAlreadyExistsException("email", req.email());
        Client client = clientMapper.toEntity(req);
        client.setPassHash(passwordEncoder.encode(req.password()));
        logger.log("new client saved: " + client);
        clientRepo.save(client);

        return clientMapper.toDto(client);
    }

    public ResponseBodyDto editClientHelper(EditClientDto req, Client client){

        if(req.email() != null)
            if(this.validateClientEmail(req.email()))
                if(!client.getEmail().equals(req.email()))
                    client.setEmail(req.email());
                else
                    throw new AuthException("unauthorized access", "you may not edit this client");
            else
                throw new BadRequestException("email is invalid");

        if(req.phoneNumber() != null)
            if(this.validateClientPhone(req.phoneNumber()))
                if(!client.getPhoneNumber().equals(req.phoneNumber()))
                    client.setPhoneNumber(req.phoneNumber());
                else
                    throw new AuthException("unauthorized access", "you may not edit this client");
            else
                throw new BadRequestException("phone number is invalid");

        if(req.username() != null)
            if(this.validateClientUsername(req.username()))
                if(!client.getUsername().equals(req.username()))
                    client.setUsername(req.username());
                else
                    throw new AuthException("unauthorized access", "you may not edit this client");
            else
                throw new BadRequestException("username is invalid");

        return clientMapper.toDto(clientRepo.save(client));
    }

    private void validateClientDetails(String email, String phoneNumber, String username){
        if(!this.validateClientEmail(email)) //TODO: replace the return true in these methods or remove them
            throw new BadRequestException("email + " + email + " is invalid");
        if(!this.validateClientPhone(phoneNumber))
            throw new BadRequestException("phone number " + phoneNumber + " is invalid");
        if(!this.validateClientUsername(username))
            throw new BadRequestException("username " + username + " is invalid");
    }
    private boolean validateClientEmail(String email){
        return true;//check if email is valid with an otp. throw exception here if general above is unsuitable
    }
    private boolean validateClientPhone(String phoneNumber){
        return true;//check if phone number is valid with an otp. throw exception here if general above is unsuitable
    }
    private boolean validateClientUsername(String username){
        //+ name is not offensive throw exception here if general above is unsuitable
        Client client = clientRepo.findByUsername(username).orElse(null);
        if(client != null)
            throw new ClientAlreadyExistsException("username", username);
        return true;
    }
}
