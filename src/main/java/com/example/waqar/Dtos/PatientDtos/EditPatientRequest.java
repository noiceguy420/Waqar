package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import com.example.waqar.Entities.ChronicDiseases;
import com.example.waqar.Entities.Gender;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public record EditPatientRequest(@Size(max = 100) String name, @Past LocalDate dateOfBirth, Gender gender,
                                 String phoneNumber, Set<ChronicDiseases> chronicDiseases) implements RequestBodyDto {
}
