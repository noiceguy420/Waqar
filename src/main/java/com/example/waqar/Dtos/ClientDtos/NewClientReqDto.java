package com.example.waqar.Dtos.ClientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import com.example.waqar.Entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link Client}
 */
public record NewClientReqDto(@NotBlank @Size(max = 70) String email,
                              @NotBlank @Size(max = 30) @Pattern(regexp = "^((?!@).)*$", message = "The '@' character is not allowed") String username,
                              @NotBlank @Size(max = 15) String phoneNumber,
                              @NotBlank @Size(max = 70) String password) implements RequestBodyDto {
}