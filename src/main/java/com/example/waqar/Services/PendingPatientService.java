package com.example.waqar.Services;

import com.example.waqar.Dtos.PendingPatientDtos.NewPendingPendingRequest;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientDto;
import com.example.waqar.Entities.PendingPatient;
import com.example.waqar.Mappers.PendingPatientMapper;
import com.example.waqar.Repositories.PendingPatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PendingPatientService {

    private final PendingPatientRepo pendingPatientRepo;
    private PendingPatientMapper pendingPatientMapper;

    public PendingPatientDto newPendingPatientHelper(NewPendingPendingRequest req) {
        //add any duplication checks here if any. none will be added based on name
        PendingPatient tmpPat = pendingPatientMapper.toEntity(req);
        pendingPatientRepo.save(tmpPat);
        System.out.println("New Patient has been saved: "  + tmpPat);
        return pendingPatientMapper.toDto(tmpPat);
    }
}
