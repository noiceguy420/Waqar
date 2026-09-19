package com.example.waqar.Dtos.PendingPatientDtos;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewPendingPendingRequest implements CustomWaqarDto {
    @NotBlank
    @Size(max = 100)
    String patientName;
    @NotNull
    @Past
    LocalDate dateOfBirth;
    @NotNull
    Gender gender;
    @NotBlank
    String phoneNumber;
    @NotNull
    Client client;
}
