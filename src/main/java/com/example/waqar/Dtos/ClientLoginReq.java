package com.example.waqar.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientLoginReq implements CustomWaqarDto{
    private String identifier; //email OR username decided by the existence of the @ symbol
    private String password;
}
