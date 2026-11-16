package com.example.waqar.Dtos.AppointmentDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentSetItemDto implements ResponseBodyDto {
    @NotNull
    private final LocalDateTime dateTime;

    @Size(max = 300)
    @NotNull
    private final String complaint;

    @NotNull
    private Boolean needsADoctor;

    @NotNull
    private Boolean wentToADoctor;
}
