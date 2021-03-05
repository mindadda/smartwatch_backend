package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.ScreenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenDetails, String> {

    @Query(value = "SELECT * FROM screen WHERE screen_name = :#{#screenName}", nativeQuery = true)
    public String findScreenName(String screenName);

    @Query(value = "SELECT * FROM screen WHERE screen_name LIKE %:screenName%", nativeQuery = true)
    public List<ScreenDetails> findByName(String screenName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM screen where screen_id = :#{#screenId}", nativeQuery = true)
    public void deleteScreen(String screenId);

    @Query(value = "SELECT * FROM screen WHERE department_id= :#{#departmentId}", nativeQuery = true)
    public List<ScreenDetails> findByDepartmentId(String departmentId);

}
