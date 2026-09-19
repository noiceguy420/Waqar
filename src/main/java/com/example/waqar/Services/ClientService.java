package com.example.waqar.Services;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.ClientDtos.NewClientReqDto;
import com.example.waqar.Entities.Client;
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

    @Override
    public UserDetails loadUserByUsername(@NonNull String identifier) throws UsernameNotFoundException {
        Client client;
        if(identifier.contains("@"))
            client = clientRepo.findByEmail(identifier).orElseThrow(() -> new ClientNotFoundException("email", identifier));
        else
            client = clientRepo.findByUsername(identifier).orElseThrow(() -> new ClientNotFoundException("username", identifier));
        return new User(client.getUsername(), client.getPassHash(), Collections.emptyList());
    }

    public CustomWaqarDto newClientHelper(NewClientReqDto req){

        if(clientRepo.existsByEmail(req.getEmail()))
            return new ErrorDto("Email already exists");
        Client client = clientMapper.toEntity(req);
        client.setPassHash(passwordEncoder.encode(req.getPassword()));
        System.out.println(client);
        clientRepo.save(client);

        return clientMapper.toDto(client);
    }

}
