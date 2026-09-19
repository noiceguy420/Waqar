package com.example.waqar.Mappers;

import com.example.waqar.Dtos.PendingPatientDtos.NewPendingPendingRequest;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientDto;
import com.example.waqar.Entities.PendingPatient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PendingPatientMapper {
    PendingPatient toEntity(NewPendingPendingRequest req);

    PendingPatientDto toDto(PendingPatient pendingPatient);
}
