package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.entity.Country;
import org.modelmapper.ModelMapper;

import java.util.List;

@SuppressWarnings("serial")
public class CountryConverter {

    public static List<CountryDTO> getCountryDTOListFromEntityList(List<Country> countries) {
        return new ModelMapper().map(countries, new TypeToken<List<CountryDTO>>() {
        }.getType());
    }


    public static CountryDTO getCountryDtoFromEntity(Country country) {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper.map(country, CountryDTO.class);
    }
}
