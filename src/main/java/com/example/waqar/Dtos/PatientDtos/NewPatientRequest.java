package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import com.example.waqar.Entities.ChronicDiseases;
import com.example.waqar.Entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public record NewPatientRequest(@NotBlank @Size(max = 100) String name, @NotNull @Past LocalDate dateOfBirth,
                                @NotNull Gender gender, @NotBlank String phoneNumber,
                                Set<ChronicDiseases> chronicDiseases) implements RequestBodyDto {
}
