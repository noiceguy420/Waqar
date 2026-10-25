package com.example.waqar.Dtos.ClientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for {@link com.example.waqar.Entities.Client}
 */
@Data
public class NewClientReqDto implements RequestBodyDto {
    @NotBlank
    @Size(max = 70)
    private String email;
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^((?!@).)*$", message = "The '@' character is not allowed")
    private String username;
    @NotBlank
    @Size(max = 15)
    private String phoneNumber;
    @NotBlank
    @Size(max = 70)
    private String password;
}