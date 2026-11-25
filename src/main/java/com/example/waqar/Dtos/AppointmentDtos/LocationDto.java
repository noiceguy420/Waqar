package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import jakarta.validation.constraints.NotNull;

public record LocationDto(@NotNull Double latitude, @NotNull Double longitude) implements ResponseBodyDto {
}
