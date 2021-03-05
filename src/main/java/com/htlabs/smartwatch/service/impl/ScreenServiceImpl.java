package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.ScreenDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.Department;
import com.htlabs.smartwatch.entity.RegionDetails;
import com.htlabs.smartwatch.entity.ScreenDetails;
import com.htlabs.smartwatch.entity.converter.RegionConverter;
import com.htlabs.smartwatch.entity.converter.ScreenConverter;
import com.htlabs.smartwatch.repository.DepartmentRepository;
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

    @Override
    public void createScreen(String screenName, String departmentName) {
        String departmentId = departmentRepository.findByDepartmentName(departmentName);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DEPARTMENT);
        }

        String screenname = screenRepository.findScreenName(screenName);
        if (screenname == null){
            log.info("Creating Screen:  {}", screenName);
            String screenId = UUID.randomUUID().toString();
            ScreenDetails screen = new ScreenDetails(screenId , screenName);
            screen.setDepartment(department);
            screen.setCreatedAt(new Date());
            screen.setUpdatedAt(new Date());
            screenRepository.save(screen);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.SCREEN_EXIST);
        }
    }

    @Override
    public void updateScreen(String screenId , String screenName, String departmentName) {
        String departmentId = departmentRepository.findByDepartmentName(departmentName);
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
        screenRepository.deleteScreen(screenId);
    }

    @Override
    public List<ScreenDTO> getScreenByDepartment(String departmentName) {
        log.info("Retrieving Screen by DepartmentId ");
        String departmentId = departmentRepository.findByDepartmentName(departmentName);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if(department == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DEPARTMENT);
        }

        List<ScreenDetails> screenDetails = screenRepository.findByDepartmentId(departmentId);
        return ScreenConverter.getScreenDTOListFromEntityList(screenDetails);
    }

}
