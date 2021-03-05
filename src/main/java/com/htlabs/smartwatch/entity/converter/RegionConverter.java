package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.dto.RegionDetailsDTO;
import com.htlabs.smartwatch.entity.RegionDetails;
import org.modelmapper.ModelMapper;

import java.util.List;

@SuppressWarnings("serial")
public class RegionConverter {

    public RegionConverter(){

    }

    public static List<RegionDetailsDTO> getRegionDTOListFromEntityList(List<RegionDetails> regionDetails) {
        return new ModelMapper().map(regionDetails, new TypeToken<List<RegionDetailsDTO>>() {
        }.getType());
    }

    public static RegionDetailsDTO getRegionDtoFromEntity(RegionDetails regionDetails) {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper.map(regionDetails, RegionDetailsDTO.class);
    }
}
