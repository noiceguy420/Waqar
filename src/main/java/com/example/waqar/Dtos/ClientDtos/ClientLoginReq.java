package com.example.waqar.Dtos.ClientDtos;

import com.example.waqar.Dtos.RequestBodyDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientLoginReq implements RequestBodyDto {
    private String identifier; //email OR username decided by the existence of the @ symbol
    private String password;
}
