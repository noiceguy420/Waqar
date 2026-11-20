package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.ResponseBodyDto;

import java.util.Map;

public record ErrorMapDto(Map<String, String> errors) implements ResponseBodyDto {
}
