package com.htlabs.smartwatch.entity.converter;

import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.entity.UserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.util.List;


@SuppressWarnings("serial")
public class UserConverter {

    private UserConverter(){
    }

    public static void getUserDetailEntityFromDto(UserDetailsDTO source, UserDetails destination) {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<UserDetailsDTO, UserDetails>() {
            @Override
            protected void configure() {
                skip(destination.getUserId());
            }
        });
        mapper.map(source, destination);
    }

    public static UserDetailsDTO getUserDetailDtoFromEntity(UserDetails userDetail) {
        return getUserDtoMapperWithTypeMap().map(userDetail, UserDetailsDTO.class);
    }

    private static ModelMapper getUserDtoMapperWithTypeMap() {
        ModelMapper mapper = getUserDtoMapper();
        mapper.typeMap(UserDetails.class, UserDetailsDTO.class).setPostConverter(context -> {
            return context.getDestination();
        });

        return mapper;
    }

    private static ModelMapper getUserDtoMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<UserDetails, UserDetailsDTO>() {
            @Override
            protected void configure() {
//                skip(destination.getPassword());
            }
        });
        return mapper;
    }

    public static List<UserDetailsDTO> getListUserDetailsDtoFromEntityList(List<UserDetails> userDetails) {
        return new ModelMapper().map(userDetails, new TypeToken<List<UserDetailsDTO>>() {
        }.getType());
    }
}

