package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.ScreenDTO;
import com.htlabs.smartwatch.entity.*;
import com.htlabs.smartwatch.entity.converter.RegionConverter;
import com.htlabs.smartwatch.entity.converter.ScreenConverter;
import com.htlabs.smartwatch.repository.DepartmentRepository;
import com.htlabs.smartwatch.repository.PanelRepository;
import com.htlabs.smartwatch.repository.ScreenRepository;
import com.htlabs.smartwatch.service.ScreenService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PanelRepository panelRepository;

    @Override
    public void createScreen(String screenName, String departmentId, Integer rowNo, Integer colNo) {
//        String departmentId = departmentRepository.findByDepartmentName(departmentName);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DEPARTMENT);
        }

        String screenname = screenRepository.findScreenName(screenName);
        if (screenname == null){
            log.info("Creating Screen:  {}", screenName);
            String screenId = UUID.randomUUID().toString();
            ScreenDetails screen = new ScreenDetails(screenId , screenName, rowNo, colNo);
            screen.setDepartment(department);
            screen.setCreatedAt(new Date());
            screen.setUpdatedAt(new Date());
            screenRepository.save(screen);

            createPanel(screenId , rowNo , colNo);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.SCREEN_EXIST);
        }

    }

    private void createPanel(String screenId, Integer rowNo, Integer colNo) {
        log.info("Creating Panel ");

        ScreenDetails screenDetails = screenRepository.findById(screenId).orElse(null);
        if(screenDetails == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_SCREEN);
        }

        for (int i = 0 ; i < rowNo; i++){
            for (int j = 0; j < colNo; j++){
                String panelId = UUID.randomUUID().toString();
                String panelName = "Panel";
                Panel panel = new Panel();
                panel.setPanelId(panelId);
                panel.setPanelName(panelName);
                panel.setRowNo(i);
                panel.setColumnNo(j);
                panel.setScreen(screenDetails);
                panel.setCreatedAt(new Date());
                panel.setUpdatedAt(new Date());
                panelRepository.save(panel);
            }
        }

    }

    @Override
    public void updateScreen(String screenId , String screenName, String departmentId) {
//        String departmentId = departmentRepository.findByDepartmentName(departmentName);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DEPARTMENT);
        }

        ScreenDetails screen = screenRepository.findById(screenId).orElse(null);
        if (screen == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_SCREEN);
        }
        else {
            String screenname = screenRepository.findScreenName(screenName);
            if (screenname == null){
                log.info("Updating Screen:  {}", screenName);
                screen.setScreenName(screenName);
                screen.setDepartment(department);
                screen.setUpdatedAt(new Date());
                screenRepository.save(screen);
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.SCREEN_EXIST);
            }

        }
    }

    @Override
    public List<ScreenDTO> getAllScreens() {
        log.info("Retrieving all the Screens.");
        List<ScreenDetails> screenDetails = screenRepository.findAll();
        return ScreenConverter.getScreenDTOListFromEntityList(screenDetails);
    }

    @Override
    public ScreenDTO getScreenById(String screenId) {
        log.info("Retrieving the Screen Details : {}", screenId);
        ScreenDetails screenDetails = screenRepository.findById(screenId).orElse(null);
        return ScreenConverter.getScreenDtoFromEntity(screenDetails);
    }

    @Override
    public List<ScreenDTO> getScreenByName(String screenName) {
        List<ScreenDetails> screenDetails = screenRepository.findByName(screenName);
        if (screenDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_SCREEN);
        }
        return ScreenConverter.getScreenDTOListFromEntityList(screenDetails);
    }

    @Override
    public void deleteScreen(String screenId) {
        log.info("Deleting Screen : {}", screenId);
        ScreenDetails screenDetails = screenRepository.findById(screenId).orElse(null);
        if (screenDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_SCREEN);
        }
        panelRepository.deletePanelWithScreen(screenId);
        screenRepository.deleteScreen(screenId);
    }

    @Override
    public List<ScreenDTO> getScreenByDepartment(String departmentId) {
        log.info("Retrieving Screen by DepartmentId ");
//        String departmentId = departmentRepository.findByDepartmentName(departmentName);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DEPARTMENT);
        }

        List<ScreenDetails> screenDetails = screenRepository.findByDepartmentId(departmentId);
        return ScreenConverter.getScreenDTOListFromEntityList(screenDetails);
    }

}
