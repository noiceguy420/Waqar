package com.example.waqar.Controllers;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Dtos.PendingPatientDtos.NewPendingPendingRequest;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientDto;
import com.example.waqar.Services.PendingPatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("my-patients")
@AllArgsConstructor
public class PendingPatientController {
    private PendingPatientService pendingPatientService;

    @PostMapping("new")
    public ResponseEntity<? extends CustomWaqarDto> createPendingPatient(@RequestBody @Valid NewPendingPendingRequest req, UriComponentsBuilder uriBuilder) {
        PendingPatientDto res = pendingPatientService.newPendingPatientHelper(req);

        URI uri = uriBuilder.path("/my-patients").buildAndExpand(res).toUri();
        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping
    public ResponseEntity<? extends CustomWaqarDto> getPendingPatients() {
        return null;
    }
}
