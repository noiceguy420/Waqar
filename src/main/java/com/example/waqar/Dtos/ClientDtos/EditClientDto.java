package com.example.waqar.Dtos.ClientDtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EditClientDto(@Size(max = 70) String email,
                            @Size(max = 30) @Pattern(regexp = "^((?!@).)*$", message = "The '@' character is not allowed") String username,
                            @Size(max = 15) String phoneNumber) {
}
