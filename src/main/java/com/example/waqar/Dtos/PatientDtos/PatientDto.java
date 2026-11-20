package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.ChronicDiseases;
import com.example.waqar.Entities.Gender;
import java.time.LocalDate;
import java.util.Set;

public record PatientDto(String name, LocalDate dateOfBirth, Gender gender, String phoneNumber, ClientDto client,
                         Set<ChronicDiseases> chronicDiseases) implements ResponseBodyDto {
}
