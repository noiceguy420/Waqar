package com.example.waqar.Controllers;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Dtos.MiscDtos.ErrorDto;
import com.example.waqar.Dtos.PendingPatientDtos.NewPendingPatientRequest;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientDto;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientSetDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Services.PendingPatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("my patients")
@AllArgsConstructor
public class PendingPatientController {
    private PendingPatientService pendingPatientService;

    @PostMapping("new")
    public ResponseEntity<? extends ResponseBodyDto> createPendingPatient(@RequestBody @Valid NewPendingPatientRequest req, UriComponentsBuilder uriBuilder) {
        PendingPatientDto res = pendingPatientService.newPendingPatientHelper(req);

        URI uri = uriBuilder.path("/my patients").buildAndExpand(res).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping("view my patients")
    public ResponseEntity<? extends ResponseBodyDto> getPendingPatients() {
        PendingPatientSetDto set = pendingPatientService.getPendingPatientsHelper();

        if(set.getPendingPatientsCount() < 1) {
            System.out.println("No pending patient found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("no pending patients found for current user"));
        }
        return ResponseEntity.ok(set);
    }
}
