package com.example.waqar.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 * DTO for {@link com.example.waqar.Entities.Client}
 */
@Value
public class NewClientReqDto implements CustomWaqarDto {
    @NotBlank
    @Size(max = 70)
    String email;
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^((?!@).)*$", message = "The '@' character is not allowed")
    String username;
    @NotBlank
    @Size(max = 15)
    String phoneNumber;
    @NotBlank
    @Size(max = 70)
    String password;
}