package com.example.waqar.Services.SecurityServices;

import com.example.waqar.Configs.JwtConfig;
import com.example.waqar.Dtos.ClientDtos.ClientLoginReq;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.ClientSession;
import com.example.waqar.Exceptions.AuthException;
import com.example.waqar.Exceptions.ClientNotFoundException;
import com.example.waqar.Exceptions.InvalidJwtException;
import com.example.waqar.Exceptions.SessionNotFoundException;
import com.example.waqar.Repositories.ClientRepo;
import com.example.waqar.Repositories.SessionRepo;
import com.example.waqar.Services.LoggerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class AuthService {

    private final ClientRepo clientRepo;
    private final AuthenticationManager authenticationManager;
    private final LoggerService logger;
    private final SessionRepo sessionRepo;
    private JwtConfig jwtConfig;
    private JwtService jwtService;

    public Client getLoggedInClient(){
        try {
            Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(id == null)
                throw new AuthException("something went wrong", "please try again. if the issue persists contact us");
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
                    .orElseThrow(() -> new AuthException("identifier or password are incorrect", "bad credentials"));

    }

    public void createClientSession(String jwtToken){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedToken = md.digest(jwtToken.getBytes(StandardCharsets.UTF_8));

            Integer cid = jwtService.parseToken(jwtToken).getClientId();
            Client client = clientRepo.findById(cid).orElseThrow(() -> new ClientNotFoundException("ID", cid.toString()));

            sessionRepo.revokeActiveByClient(client);
            ClientSession session = new ClientSession();
            session.setTokenHash(hashedToken);
            session.setClient(client);
            sessionRepo.saveAndFlush(session);
        } catch (NoSuchAlgorithmException e) {
            logger.log("no such algorithm exception thrown");
            throw new RuntimeException("no such algorithm exception thrown");
        }
        catch (Exception e){
            logger.log("exception caught: "  + e.getMessage());
            throw new RuntimeException("exception caught");
        }
    }

    @Transactional
    public boolean validateSession(String jwtToken){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedToken = md.digest(jwtToken.getBytes(StandardCharsets.UTF_8));

            ClientSession session = sessionRepo.findByTokenHash(hashedToken).orElseThrow(() -> new SessionNotFoundException("hashedToken: " + Arrays.toString(hashedToken)));
            Client client =  session.getClient();

            if(!session.getActive()) {
                revokeAllClientSessions(client);
                return false;
            }

            LocalDateTime expiry = session.getCreatedAt().plus(jwtConfig.getRefreshExpiration(), ChronoUnit.MILLIS);
            if (expiry.isBefore(LocalDateTime.now())) {
                sessionRepo.revokeActiveByClient(client);
                throw new InvalidJwtException("Refresh token expired");
            }
            sessionRepo.saveAndFlush(session);
            return true;
        } catch (NoSuchAlgorithmException e) {
            logger.log("no such algorithm exception thrown");
            throw new RuntimeException("no such algorithm exception thrown");
        }
    }

    private void revokeAllClientSessions(Client client){
        sessionRepo.revokeAllClientSessions(client);
        logger.log("revoked all client sessions for client: "  + client);
    }
}
