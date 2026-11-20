package com.example.waqar.Dtos.ClientDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link Client}
 */
public record ClientDto(@NotNull @Size(max = 70) String email, @NotNull @Size(max = 30) String username,
                        @NotNull @Size(max = 15) String phoneNumber) implements ResponseBodyDto {
}