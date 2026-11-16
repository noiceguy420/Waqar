package com.example.waqar.Mappers;

import com.example.waqar.Dtos.AppointmentDtos.AppointmentSetDto;
import com.example.waqar.Entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    AppointmentSetDto toSetDto(Set<Appointment> appointments);
}
