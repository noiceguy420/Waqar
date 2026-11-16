package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import lombok.Data;
import java.util.Set;

@Data
public class AppointmentSetDto implements ResponseBodyDto {
    private final Set<AppointmentSetItemDto> appointmentSetItems;
    private final ClientDto client;
}
