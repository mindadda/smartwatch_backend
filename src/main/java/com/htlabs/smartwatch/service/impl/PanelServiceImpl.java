package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.PanelDTO;
import com.htlabs.smartwatch.entity.Panel;
import com.htlabs.smartwatch.entity.converter.PanelConverter;
import com.htlabs.smartwatch.repository.PanelRepository;
import com.htlabs.smartwatch.service.IotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IotServiceImpl implements IotService {

    @Autowired
    private PanelRepository panelRepository;

    @Override
    public List<PanelDTO> getPanelValue() {
        log.info("Retrieving the PanelValue.");
        List<Panel> panels = panelRepository.findAll();
        return PanelConverter.getPanelDTOListFromEntityList(panels);
    }
}
