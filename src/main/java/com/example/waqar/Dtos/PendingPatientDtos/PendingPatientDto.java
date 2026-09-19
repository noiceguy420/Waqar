package com.example.waqar.Dtos.PendingPatientDtos;

import com.example.waqar.Dtos.CustomWaqarDto;
import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.Gender;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class PendingPatientDto implements CustomWaqarDto {
    private final String patientName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String phoneNumber;
    private final Client client;
}
