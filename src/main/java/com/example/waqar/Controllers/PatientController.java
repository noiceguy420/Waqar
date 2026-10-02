package com.example.waqar.Controllers;

import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.PatientDtos.NewPatientRequest;
import com.example.waqar.Dtos.PatientDtos.PatientDto;
import com.example.waqar.Dtos.PatientDtos.PatientSetDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Services.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.example.waqar.Services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("my patients")
@AllArgsConstructor
public class PatientController {
    private PatientService PatientService;
    private LoggerService logger;

    @PostMapping("new")
    public ResponseEntity<? extends ResponseBodyDto> createPatient(@RequestBody @Valid NewPatientRequest req, UriComponentsBuilder uriBuilder) {
        PatientDto res = PatientService.newPatientHelper(req);

        URI uri = uriBuilder.path("/my patients").buildAndExpand(res).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping("view my patients")
    public ResponseEntity<? extends ResponseBodyDto> getPatients() {
        PatientSetDto set = PatientService.getPatientsHelper();

        if(set.getPatientsCount() < 1) {
            logger.log("No pending patient found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("no pending patients found for current user"));
        }
        return ResponseEntity.ok(set);
    }
}
