package com.example.waqar.Services;

import com.example.waqar.Dtos.PendingPatientDtos.NewPendingPatientRequest;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientDto;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientSetDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.PendingPatient;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Mappers.PendingPatientMapper;
import com.example.waqar.Repositories.PendingPatientRepo;
import com.example.waqar.Services.SecurityServices.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PendingPatientService {

    private final PendingPatientRepo pendingPatientRepo;
    private PendingPatientMapper pendingPatientMapper;
    private AuthService authService;
    private ClientMapper clientMapper;
    private LoggerService logger;

    public PendingPatientDto newPendingPatientHelper(NewPendingPatientRequest req) {
        //add any duplication checks here if any. none will be added based on the name field
        PendingPatient tmpPat = pendingPatientMapper.toEntity(req);
        tmpPat.setClient(authService.getLoggedInClient());
        pendingPatientRepo.save(tmpPat);
        logger.log("New Patient has been saved: "  + tmpPat);
        return pendingPatientMapper.toDto(tmpPat);
    }

    public PendingPatientSetDto getPendingPatientsHelper() {
        Client client = authService.getLoggedInClient();
        return new PendingPatientSetDto(pendingPatientMapper.setToDtos(client.getPendingPatients()), clientMapper.toDto(client));
    }
}
