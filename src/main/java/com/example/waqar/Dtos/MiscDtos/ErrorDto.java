package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.CustomWaqarDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto implements CustomWaqarDto {
    private String message;
}
