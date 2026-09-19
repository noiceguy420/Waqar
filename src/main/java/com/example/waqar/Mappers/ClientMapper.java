package com.example.waqar.Mappers;

import com.example.waqar.Dtos.ClientDtos.ClientDto;
import com.example.waqar.Dtos.ClientDtos.NewClientReqDto;
import com.example.waqar.Entities.Client;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper{
    Client toEntity(ClientDto clientDto);
    Client toEntity(NewClientReqDto newClientDto);

    ClientDto toDto(Client client);
}