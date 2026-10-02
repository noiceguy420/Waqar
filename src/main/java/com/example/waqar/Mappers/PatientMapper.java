package com.example.waqar.Mappers;

import com.example.waqar.Dtos.PatientDtos.NewPatientRequest;
import com.example.waqar.Dtos.PatientDtos.PatientDto;
import com.example.waqar.Dtos.PatientDtos.PatientSetItemDto;
import com.example.waqar.Entities.Patient;
import org.mapstruct.*;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    Patient reqToEntity(NewPatientRequest req);

    PatientDto toDto(Patient Patient);

    Set<PatientSetItemDto> setToDtos(Set<Patient> PatientSet);
}
