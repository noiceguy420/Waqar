package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class PatientSetDto implements ResponseBodyDto {
    private final Set<PatientSetItemDto> PatientDtos;
    private final ClientDto client;

    public int getPatientsCount() {
        return PatientDtos.size();
    }
}
