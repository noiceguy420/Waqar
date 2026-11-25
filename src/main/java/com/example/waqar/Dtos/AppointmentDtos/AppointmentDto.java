package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.PatientDtos.PatientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.AppointmentStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AppointmentDto(@Valid PatientDto patient, @Size(max = 300) @NotNull String complaint,
                             @NotNull Boolean needsADoctor, @NotNull Boolean wentToADoctor,
                             @NotNull LocalDateTime dateTime,
                             @NotNull AppointmentStatus status) implements ResponseBodyDto {

}
