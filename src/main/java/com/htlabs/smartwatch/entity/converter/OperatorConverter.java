package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.dto.OperatorDetailsDTO;
import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.OperatorDetails;
import com.htlabs.smartwatch.entity.UserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;

@SuppressWarnings("serial")
public class OperatorConverter {

    private OperatorConverter(){

    }
    public static List<OperatorDetailsDTO> getOperatorDetailsDTOListFromEntityList(List<OperatorDetails> operatorDetails) {
        return new ModelMapper().map(operatorDetails, new TypeToken<List<OperatorDetailsDTO>>() {
        }.getType());
    }


    public static OperatorDetailsDTO getOperatorDetailsDtoFromEntity(OperatorDetails operatorDetails) {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper.map(operatorDetails, OperatorDetailsDTO.class);
    }
}
