package com.example.waqar.Services;

import com.example.waqar.Dtos.PatientDtos.EditPatientRequest;
import com.example.waqar.Dtos.PatientDtos.NewPatientRequest;
import com.example.waqar.Dtos.PatientDtos.PatientDto;
import com.example.waqar.Dtos.PatientDtos.PatientSetDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.Patient;
import com.example.waqar.Exceptions.AuthException;
import com.example.waqar.Exceptions.PatientNotFoundException;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Mappers.PatientMapper;
import com.example.waqar.Repositories.PatientRepo;
import com.example.waqar.Services.SecurityServices.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepo patientRepo;
    private PatientMapper patientMapper;
    private AuthService authService;
    private ClientMapper clientMapper;
    private LoggerService logger;

    public PatientDto newPatientHelper(NewPatientRequest req) {
        //add any duplication checks here if any. none will be added based on the name field
        Patient tmpPat = patientMapper.reqToEntity(req);
        tmpPat.setClient(authService.getLoggedInClient());
        patientRepo.save(tmpPat);
        logger.log("New Patient has been saved: "  + tmpPat);
        return patientMapper.toDto(tmpPat);
    }

    public PatientSetDto getPatientsHelper() {
        Client client = authService.getLoggedInClient();
        return new PatientSetDto(patientMapper.setToDtos(client.getPatients()), clientMapper.toDto(client));
    }

    public PatientDto editPatientHelper(Integer pid, EditPatientRequest req) {
        Patient tmpPat = patientRepo.findById(pid).orElseThrow(() ->
                new PatientNotFoundException("id",pid.toString()));

        if (!Objects.equals(tmpPat.getClient().getId(), authService.getLoggedInClient().getId()))
            throw new AuthException("client mismatch", "this client is not authorized to edit this patient");
        if(req.getName() != null && !req.getName().isEmpty())
            tmpPat.setName(req.getName());
        if(req.getChronicDiseases() != null )
            tmpPat.setChronicDiseases(req.getChronicDiseases());
        if(req.getGender() != null )
            tmpPat.setGender(req.getGender());
        if(req.getPhoneNumber() != null )
            tmpPat.setPhoneNumber(req.getPhoneNumber());
        if(req.getDateOfBirth() != null )
            tmpPat.setDateOfBirth(req.getDateOfBirth());

        patientRepo.save(tmpPat);
        logger.log("Edited Patient has been saved: "  + tmpPat);
        return patientMapper.toDto(tmpPat);
    }
}
