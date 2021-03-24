package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , String> {

    @Query(value = "SELECT department_name FROM department WHERE department_name LIKE %:departmentName%", nativeQuery = true)
    public String findDepartmentName(String departmentName);

    @Query(value = "SELECT * FROM department WHERE department_name LIKE %:departmentName%", nativeQuery = true)
    public List<Department> findByName(String departmentName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM department where department_id = :#{#departmentId}", nativeQuery = true)
    public void deleteDepartment(String departmentId);

    @Query(value = "SELECT department_id FROM department WHERE department_name= :#{#departmentName}", nativeQuery = true)
    public String findByDepartmentName(String departmentName);

    @Query(value = "SELECT * FROM department WHERE client_id = :#{#clientId} AND location_id = :#{#locationId}" , nativeQuery = true)
    public List<Department> findByClientLocation(String clientId , String locationId);
}
