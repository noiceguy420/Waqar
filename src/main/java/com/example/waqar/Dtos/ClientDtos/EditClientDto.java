package com.example.waqar.Dtos.ClientDtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditClientDto {
    @Size(max = 70)
    private String email;
    @Size(max = 30)
    @Pattern(regexp = "^((?!@).)*$", message = "The '@' character is not allowed")
    private String username;
    @Size(max = 15)
    private String phoneNumber;
}
