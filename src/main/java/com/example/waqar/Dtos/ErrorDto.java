package com.example.waqar.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto implements CustomWaqarDto {
    private String message;
}
