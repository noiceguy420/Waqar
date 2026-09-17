package com.example.waqar.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenRes implements CustomWaqarDto{
    private final String AccessToken;
}
