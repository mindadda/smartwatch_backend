package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.ScreenDTO;

import java.util.List;

public interface ScreenService {
    
    public Integer createScreen(String screenName, String departmentId, Integer rowNo, Integer colNo);

    public Integer updateScreen(String screenId ,String screenName, String departmentId);

    public List<ScreenDTO> getAllScreens();

    public ScreenDTO getScreenById(String screenId);

    public List<ScreenDTO> getScreenByName(String screenName);

    public void deleteScreen(String screenId);

    public List<ScreenDTO> getScreenByDepartment(String departmentId);
}
