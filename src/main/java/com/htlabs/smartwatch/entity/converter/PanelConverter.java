package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.PanelDTO;
import com.htlabs.smartwatch.entity.Panel;
import org.modelmapper.ModelMapper;

import java.util.List;

@SuppressWarnings("serial")
public class PanelConverter {

    private PanelConverter() {

    }


    public static List<PanelDTO> getPanelDTOListFromEntityList(List<Panel> panels) {
        return new ModelMapper().map(panels, new TypeToken<List<PanelDTO>>() {
        }.getType());
    }
}
