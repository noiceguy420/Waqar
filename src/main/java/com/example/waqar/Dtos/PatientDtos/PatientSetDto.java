package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import jakarta.validation.Valid;

import java.util.Set;

public record PatientSetDto (@Valid Set<PatientSetItemDto> PatientDtos,
                             @Valid ClientDto client) implements ResponseBodyDto {

}
