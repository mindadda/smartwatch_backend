package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.ScreenDTO;

import java.util.List;

public interface ScreenService {
    
    public void createScreen(String screenName, String departmentName, Integer rowNo, Integer colNo);

    public void updateScreen(String screenId ,String screenName, String departmentName);

    public List<ScreenDTO> getAllScreens();

    public ScreenDTO getScreenById(String screenId);

    public List<ScreenDTO> getScreenByName(String screenName);

    public void deleteScreen(String screenId);

    public List<ScreenDTO> getScreenByDepartment(String departmentName);
}
