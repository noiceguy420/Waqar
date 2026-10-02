package com.example.waqar.Services;

import com.example.waqar.Dtos.PatientDtos.NewPatientRequest;
import com.example.waqar.Dtos.PatientDtos.PatientDto;
import com.example.waqar.Dtos.PatientDtos.PatientSetDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.Patient;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Mappers.PatientMapper;
import com.example.waqar.Repositories.PatientRepo;
import com.example.waqar.Services.SecurityServices.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepo PatientRepo;
    private PatientMapper PatientMapper;
    private AuthService authService;
    private ClientMapper clientMapper;
    private LoggerService logger;

    public PatientDto newPatientHelper(NewPatientRequest req) {
        //add any duplication checks here if any. none will be added based on the name field
        Patient tmpPat = PatientMapper.toEntity(req);
        tmpPat.setClient(authService.getLoggedInClient());
        PatientRepo.save(tmpPat);
        logger.log("New Patient has been saved: "  + tmpPat);
        return PatientMapper.toDto(tmpPat);
    }

    public PatientSetDto getPatientsHelper() {
        Client client = authService.getLoggedInClient();
        return new PatientSetDto(PatientMapper.setToDtos(client.getPatients()), clientMapper.toDto(client));
    }
}
