package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto implements ResponseBodyDto {
    private String message;
}
