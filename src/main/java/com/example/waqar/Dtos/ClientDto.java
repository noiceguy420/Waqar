package com.example.waqar.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.example.waqar.Entities.Client}
 */
@Data
@Getter
@Setter
public class ClientDto implements CustomWaqarDto {
    @NotNull
    @Size(max = 70)
    String email;
    @NotNull
    @Size(max = 30)
    String username;
    @NotNull
    @Size(max = 15)
    String phoneNumber;
}