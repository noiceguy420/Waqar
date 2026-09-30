package com.example.waqar.Dtos.PendingPatientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
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
public class NewPendingPatientRequest implements RequestBodyDto {
    @NotBlank
    @Size(max = 100)
    String name;
    @NotNull
    @Past
    LocalDate dateOfBirth;
    @NotNull
    Gender gender;
    @NotBlank
    String phoneNumber;
}
