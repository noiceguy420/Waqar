package com.example.waqar.Dtos.PendingPatientDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class PendingPatientSetDto implements ResponseBodyDto {
    private final Set<PendingPatientSetItemDto> pendingPatientDtos;
    private final ClientDto client;

    public int getPendingPatientsCount() {
        return pendingPatientDtos.size();
    }
}
