package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenRes implements ResponseBodyDto {
    private final String AccessToken;
}
