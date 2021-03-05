package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    
    public void createDepartment(String clientName , String locationName, String departmentName);

    public void updateDepartment(String departmentId, String departmentName);

    public List<DepartmentDTO> getAllDepartments();

    public DepartmentDTO getDepartmentById(String departmentId);

    public List<DepartmentDTO> getDepartmentByName(String departmentName);

    public void deleteDepartment(String departmentId);
}
