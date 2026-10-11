package com.example.waqar.Dtos.PatientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import com.example.waqar.Entities.ChronicDiseases;
import com.example.waqar.Entities.Gender;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Data
public class EditPatientRequest implements RequestBodyDto {
    @Size(max = 100)
    private final String name;
    @Past
    private final LocalDate dateOfBirth;
    private final Gender gender;
    private final String phoneNumber;
    private final Set<ChronicDiseases> chronicDiseases;
}
