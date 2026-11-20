package com.example.waqar.Dtos.ClientDtos;

import com.example.waqar.Dtos.RequestBodyDto;

/**
 * @param identifier email OR username decided by the existence of the @ symbol
 */
public record ClientLoginReq(String identifier, String password) implements RequestBodyDto {
}
