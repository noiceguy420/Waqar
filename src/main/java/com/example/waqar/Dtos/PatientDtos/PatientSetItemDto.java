package com.example.waqar.Dtos.PatientDtos;
import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Entities.ChronicDiseases;
import com.example.waqar.Entities.Gender;

import java.time.LocalDate;
import java.util.Set;

public record PatientSetItemDto(String name, LocalDate dateOfBirth, Gender gender, String phoneNumber,
                                Set<ChronicDiseases> chronicDiseases) implements CustomWaqarDto {
}
