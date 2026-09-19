package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.CustomWaqarDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenRes implements CustomWaqarDto {
    private final String AccessToken;
}
