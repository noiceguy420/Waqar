package com.example.waqar.Dtos.PendingPatientDtos;

import com.example.waqar.Dtos.CustomWaqarDto;
import lombok.AllArgsConstructor;
import java.util.Set;

@AllArgsConstructor
public class PendingPatientSetDto implements CustomWaqarDto {
    private final Set<PendingPatientDto> pendingPatientDtos;

    public int getPendingPatientsCount() {
        return pendingPatientDtos.size();
    }
}
