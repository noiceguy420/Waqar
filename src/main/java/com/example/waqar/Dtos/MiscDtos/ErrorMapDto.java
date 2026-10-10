package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorMapDto implements ResponseBodyDto {
    Map<String, String> errors;
}
