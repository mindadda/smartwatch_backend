package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.ScreenDTO;
import com.htlabs.smartwatch.entity.ScreenDetails;
import org.modelmapper.ModelMapper;

import java.util.List;

@SuppressWarnings("serial")
public class ScreenConverter {

    private ScreenConverter(){

    }

    public static List<ScreenDTO> getScreenDTOListFromEntityList(List<ScreenDetails> screens) {
        return new ModelMapper().map(screens, new TypeToken<List<ScreenDTO>>() {
        }.getType());
    }

    public static ScreenDTO getScreenDtoFromEntity(ScreenDetails screen) {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper.map(screen, ScreenDTO.class);
    }
}
