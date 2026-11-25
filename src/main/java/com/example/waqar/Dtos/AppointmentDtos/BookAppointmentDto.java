package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * @param dateTime TODO: validate in create method in service
 */
public record BookAppointmentDto(@NotNull Integer patientId, @NotNull LocalDateTime dateTime,
                                 @NotNull @Valid LocationDto location) implements RequestBodyDto {
}
