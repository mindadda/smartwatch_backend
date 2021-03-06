package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    
    public Integer createDepartment(String clientId , String locationId, String departmentName);

    public Integer updateDepartment(String departmentId, String departmentName);

    public List<DepartmentDTO> getAllDepartments();

    public DepartmentDTO getDepartmentById(String departmentId);

    public List<DepartmentDTO> getDepartmentByName(String departmentName);

    public void deleteDepartment(String departmentId);

    public List<DepartmentDTO> getDepartmentByClientLocation(String clientId , String locationId);
}
