package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;

import java.util.Set;

public record AppointmentSetDto(Set<AppointmentSetItemDto> appointmentSetItems,
                                ClientDto client) implements ResponseBodyDto {
}
