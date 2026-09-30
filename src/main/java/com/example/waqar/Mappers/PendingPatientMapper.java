package com.example.waqar.Mappers;

import com.example.waqar.Dtos.PendingPatientDtos.NewPendingPatientRequest;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientDto;
import com.example.waqar.Dtos.PendingPatientDtos.PendingPatientSetItemDto;
import com.example.waqar.Entities.PendingPatient;
import org.mapstruct.*;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PendingPatientMapper {
    PendingPatient toEntity(NewPendingPatientRequest req);

    PendingPatientDto toDto(PendingPatient pendingPatient);

    Set<PendingPatientSetItemDto> setToDtos(Set<PendingPatient> pendingPatientSet);
}
