package com.example.waqar.Dtos.PendingPatientDtos;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Entities.PendingPatient;
import com.example.waqar.Mappers.PendingPatientMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class PendingPatientSetDto implements CustomWaqarDto{
    private final PendingPatientMapper pendingPatientMapper;
    private final Set<PendingPatientDto> pendingPatientDtos =  new LinkedHashSet<>();

    public boolean fillOutMyPatients(Set<PendingPatient> pendingPatients){
        try {
            for (PendingPatient pendingPatient : pendingPatients) {
                pendingPatientDtos.add(pendingPatientMapper.toDto(pendingPatient));
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

