package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.ClientDTO;


import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.entity.ClientDetails;


import com.htlabs.smartwatch.entity.UserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;


public class ClientConverter {

    private ClientConverter(){

    }

    public static void getClientDetailsEntityFromDto(ClientDTO source, ClientDetails destination) {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ClientDTO, ClientDetails>() {
            @Override
            protected void configure() {
                skip(destination.getClientId());
            }
        });
        mapper.map(source, destination);
    }

    public static ClientDTO getClientDtoFromEntity(ClientDetails clientDetails) {
        return getClientDtoMapperWithTypeMap().map(clientDetails, ClientDTO.class);
    }

    private static ModelMapper getClientDtoMapperWithTypeMap() {
        ModelMapper mapper = getUserDtoMapper();
        mapper.typeMap(ClientDetails.class, ClientDTO.class).setPostConverter(context -> {
            return context.getDestination();
        });

        return mapper;
    }

    private static ModelMapper getUserDtoMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ClientDetails, ClientDTO>() {
            @Override
            protected void configure() {
//                skip(destination.getPassword());
            }
        });
        return mapper;
    }


    public static ClientDTO getClientDTOFromEntity(ClientDetails clientDetails) {
        return new ModelMapper().map(clientDetails, new TypeToken<ClientDTO>() {
        }.getType());
    }

    public static List<ClientDTO> getClientDTOListFromEntityList(List<ClientDetails> clients) {
        return new ModelMapper().map(clients, new TypeToken<List<ClientDTO>>() {
        }.getType());
    }
}





