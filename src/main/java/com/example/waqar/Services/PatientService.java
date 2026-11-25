package com.example.waqar.Services;

import com.example.waqar.Dtos.PatientDtos.EditPatientRequest;
import com.example.waqar.Dtos.PatientDtos.NewPatientRequest;
import com.example.waqar.Dtos.PatientDtos.PatientDto;
import com.example.waqar.Dtos.PatientDtos.PatientSetDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.Patient;
import com.example.waqar.Exceptions.*;
import com.example.waqar.Mappers.ClientMapper;
import com.example.waqar.Mappers.PatientMapper;
import com.example.waqar.Repositories.PatientRepo;
import com.example.waqar.Services.SecurityServices.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;


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
        Set<Patient> patSet = client.getPatients();
        if (patSet.isEmpty()) {
            throw new NoPatientsException(client.getUsername());
        }
        return new PatientSetDto(patientMapper.setToDtos(patSet), clientMapper.toDto(client));
    }

    public PatientDto editPatientHelper(Integer pid, EditPatientRequest req) {
        if(pid == null)
            throw new BadRequestException("patient id not provided");

        Patient tmpPat = patientRepo.findById(pid).orElseThrow(() ->
                new PatientNotFoundException("id",pid.toString()));

        if (!tmpPat.clientOwnsPatient(authService.getLoggedInClient()))
            throw new ClientPatientMismatchException();
        if(req.name() != null && !req.name().isEmpty())
            tmpPat.setName(req.name());
        if(req.chronicDiseases() != null )
            tmpPat.setChronicDiseases(req.chronicDiseases());
        if(req.gender() != null )
            tmpPat.setGender(req.gender());
        if(req.phoneNumber() != null )
            tmpPat.setPhoneNumber(req.phoneNumber());
        if(req.dateOfBirth() != null )
            tmpPat.setDateOfBirth(req.dateOfBirth());

        patientRepo.save(tmpPat);
        logger.log("Edited Patient has been saved: "  + tmpPat);
        return patientMapper.toDto(tmpPat);
    }

    public PatientDto DeletePatientHelper(Integer pid) {
        if(pid == null)
            throw new BadRequestException("patient id not provided");
        Patient tmpPat = patientRepo.findById(pid).orElse(null);
        if(tmpPat == null)
            logger.log("Patient with id " + pid + " does not exist. as a deletion no exception has been thrown");
        patientRepo.deleteById(pid);
        logger.log("Patient has been deleted: "  + tmpPat);
        return patientMapper.toDto(tmpPat);
    }
}
