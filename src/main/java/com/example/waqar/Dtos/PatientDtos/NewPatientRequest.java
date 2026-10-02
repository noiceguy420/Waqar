package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import com.example.waqar.Entities.ChronicDiseases;
import com.example.waqar.Entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class NewPatientRequest implements RequestBodyDto {
    @NotBlank
    @Size(max = 100)
    private final String name;
    @NotNull
    @Past
    private final LocalDate dateOfBirth;
    @NotNull
    private final Gender gender;
    @NotBlank
    private final String phoneNumber;
    @NotNull
    private final Set<ChronicDiseases> chronicDiseases;
}
