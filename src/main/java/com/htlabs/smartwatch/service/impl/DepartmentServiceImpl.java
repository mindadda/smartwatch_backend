package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.DepartmentDTO;
import com.htlabs.smartwatch.entity.*;
import com.htlabs.smartwatch.entity.converter.DepartmentConverter;
import com.htlabs.smartwatch.repository.ClientDetailRepository;
import com.htlabs.smartwatch.repository.DepartmentRepository;
import com.htlabs.smartwatch.repository.LocationRepository;
import com.htlabs.smartwatch.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private ClientDetailRepository clientDetailRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void createDepartment(String clientId , String locationId, String departmentName) {
//        String clientId = clientDetailRepository.findByClientName(clientName);
        ClientDetails clientDetails = clientDetailRepository.findById(clientId).orElse(null);
        if (clientDetails == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_CLIENT);
        }

//        String locationId = locationRepository.findByLocationName(locationName);
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ErrorMessages.INVALID_LOCATION);
        }

        String deptName = departmentRepository.findDepartmentName(departmentName);
        if (deptName == null){
            log.info("Creating Department {} !" , departmentName);
            String departmentId = UUID.randomUUID().toString();
            Department department = new Department(departmentId , departmentName);
            department.setClientDetails(clientDetails);
            department.setLocation(location);
            department.setCreatedAt(new Date());
            department.setUpdatedAt(new Date());
            departmentRepository.save(department);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.DEPARTMENT_EXIST);
        }
    }

    @Override
    public void updateDepartment(String departmentId, String departmentName) {

        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_DEPARTMENT);
        }
        else {
            String departmentname = departmentRepository.findDepartmentName(departmentName);
            if (departmentname == null){
                log.info("Updating Department:  {}", departmentName);
                department.setDepartmentName(departmentName);
                department.setUpdatedAt(new Date());
                departmentRepository.save(department);
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.DEPARTMENT_EXIST);
            }

        }

    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        log.info("Retrieving all the Departments.");
        List<Department> departments = departmentRepository.findAll();
        return DepartmentConverter.getDepartmentDTOListFromEntityList(departments);
    }

    @Override
    public DepartmentDTO getDepartmentById(String departmentId) {
        log.info("Retrieving the Department Details : {}", departmentId);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        return DepartmentConverter.getDepartmentDtoFromEntity(department);
    }

    @Override
    public List<DepartmentDTO> getDepartmentByName(String departmentName) {
        List<Department> departments = departmentRepository.findByName(departmentName);
        if (departments == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_DEPARTMENT);
        }
        return DepartmentConverter.getDepartmentDTOListFromEntityList(departments);
    }

    @Override
    public void deleteDepartment(String departmentId) {
        log.info("Deleting Department : {}", departmentId);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_DEPARTMENT);
        }
        departmentRepository.deleteDepartment(departmentId);
    }

    @Override
    public List<DepartmentDTO> getDepartmentByClientLocation(String clientId , String locationId) {
//        String clientId = clientDetailRepository.findByClientName(clientName);
        ClientDetails clientDetails = clientDetailRepository.findById(clientId).orElse(null);
        if (clientDetails == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_CLIENT);
        }

//        String locationId = locationRepository.findByLocationName(locationName);
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ErrorMessages.INVALID_LOCATION);
        }

        List<Department> departments = departmentRepository.findByClientLocation(clientId , locationId);
        return DepartmentConverter.getDepartmentDTOListFromEntityList(departments);
    }
}
