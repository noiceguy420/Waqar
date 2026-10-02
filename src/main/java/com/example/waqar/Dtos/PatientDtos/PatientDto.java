package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ResponseBodyDto;
import com.example.waqar.Entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class PatientDto implements ResponseBodyDto {
    private final String name;
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String phoneNumber;
    private final ClientDto client;
}
