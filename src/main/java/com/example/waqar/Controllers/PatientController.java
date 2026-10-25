package com.example.waqar.Controllers;

import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.PatientDtos.EditPatientRequest;
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
    private PatientService patientService;
    private LoggerService logger;

    @PostMapping("new")
    public ResponseEntity<? extends ResponseBodyDto> createPatient(@RequestBody @Valid NewPatientRequest req, UriComponentsBuilder uriBuilder) {
        PatientDto res = patientService.newPatientHelper(req);

        URI uri = uriBuilder.path("/my patients").buildAndExpand(res).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping("view all")
    public ResponseEntity<? extends ResponseBodyDto> getPatients() {
        PatientSetDto set = patientService.getPatientsHelper();

        if(set.getPatientsCount() < 1) {
            logger.log("No pending patient found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("no pending patients found for current user"));
        }

        return ResponseEntity.ok(set);
    }

    @PatchMapping("edit/{pid}")
    public ResponseEntity<? extends ResponseBodyDto>  editPatient(
            @PathVariable(value = "pid", required = false) Integer pid, @RequestBody @Valid EditPatientRequest req,
            UriComponentsBuilder uriBuilder) {
        PatientDto res = patientService.editPatientHelper(pid, req);
        URI uri = uriBuilder.path("/my patients").buildAndExpand(res).toUri();

        return ResponseEntity.created(uri).body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<? extends ResponseBodyDto> deletePatient(@PathVariable("id") Integer pid) {
        PatientDto res = patientService.DeletePatientHelper(pid);

        return ResponseEntity.ok().body(res);
    }
}
