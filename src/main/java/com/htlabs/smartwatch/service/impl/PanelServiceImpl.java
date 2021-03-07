package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.PanelDTO;
import com.htlabs.smartwatch.entity.Department;
import com.htlabs.smartwatch.entity.Panel;
import com.htlabs.smartwatch.entity.ScreenDetails;
import com.htlabs.smartwatch.entity.converter.PanelConverter;
import com.htlabs.smartwatch.entity.converter.ScreenConverter;
import com.htlabs.smartwatch.repository.PanelRepository;
import com.htlabs.smartwatch.repository.ScreenRepository;
import com.htlabs.smartwatch.service.PanelService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class PanelServiceImpl implements PanelService {

    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public List<PanelDTO> getPanelValue() {
        log.info("Retrieving the PanelValue.");
        List<Panel> panels = panelRepository.findAll();
        return PanelConverter.getPanelDTOListFromEntityList(panels);
    }

    @Override
    public List<PanelDTO> getPanelsByScreen(String screenName) {
        log.info("Retrieving Panel by screen ");
        String screenId = screenRepository.findScreenName(screenName);
        ScreenDetails screenDetails = screenRepository.findById(screenId).orElse(null);
        if(screenDetails == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_SCREEN);
        }

        List<Panel> panels = panelRepository.findByScreenId(screenId);
        return PanelConverter.getPanelDTOListFromEntityList(panels);
    }
}
