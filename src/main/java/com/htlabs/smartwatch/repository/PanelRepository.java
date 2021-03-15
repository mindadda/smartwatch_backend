package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.Panel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PanelRepository extends JpaRepository<Panel, String> {

    @Query(value = "Select sensor_id from panel where sensor_id = :#{#sensorId}" , nativeQuery = true)
    public String findSensorId(String sensorId);

    @Query(value = "Select * from panel where sensor_id = :#{#sensId}" , nativeQuery = true)
    public Panel findBySensorId(String sensId);

    @Query(value = "SELECT * FROM panel WHERE screen_id= :#{#screenId} ORDER BY created_at ASC", nativeQuery = true)
    public List<Panel> findByScreenId(String screenId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM panel where screen_id = :#{#screenId}", nativeQuery = true)
    public void deletePanelWithScreen(String screenId);
}
