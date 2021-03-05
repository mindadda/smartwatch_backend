package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.LocationDTO;
import com.htlabs.smartwatch.entity.Location;
import org.modelmapper.ModelMapper;

import java.util.List;

@SuppressWarnings("serial")
public class LocationConverter {

    public LocationConverter(){

    }

    public static List<LocationDTO> getLocationDTOListFromEntityList(List<Location> locations) {
        return new ModelMapper().map(locations, new TypeToken<List<LocationDTO>>() {
        }.getType());
    }

    public static LocationDTO getLocationDtoFromEntity(Location location) {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper.map(location, LocationDTO.class);
    }
}
