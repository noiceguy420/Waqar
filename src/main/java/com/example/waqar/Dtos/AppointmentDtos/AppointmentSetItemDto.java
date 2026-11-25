package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AppointmentSetItemDto(@NotNull LocalDateTime dateTime, @Size(max = 300) @NotNull String complaint,
                                    @NotNull Boolean needsADoctor, @NotNull Boolean wentToADoctor,
                                    @NotNull AppointmentStatus status) implements ResponseBodyDto {
}
