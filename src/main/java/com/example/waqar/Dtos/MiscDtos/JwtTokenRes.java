package com.example.waqar.Dtos.MiscDtos;

import com.example.waqar.Dtos.ResponseBodyDto;
//something weird check it out. wouldn't automatically turn into a record class
public record JwtTokenRes (String accessToken) implements ResponseBodyDto {

}