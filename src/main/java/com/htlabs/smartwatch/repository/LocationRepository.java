package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "SELECT * FROM location WHERE location_name LIKE %:locationName%", nativeQuery = true)
    public List<Location> findByName(String locationName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM location where location_id = :#{#locationId}", nativeQuery = true)
    public void deleteLocation(String locationId);

    @Query(value = "SELECT location_name FROM location WHERE location_name= :#{#locationName}", nativeQuery = true)
    public String findLocationName(String locationName);

    @Query(value = "SELECT * FROM location WHERE region_id= :#{#regionId}", nativeQuery = true)
    public List<Location> findByRegionId(String regionId);

    @Query(value = "SELECT location_id FROM location WHERE location_name= :#{#locationName}", nativeQuery = true)
    public String findByLocationName(String locationName);

//    @Query(value = "SELECT * FROM location WHERE country_id= :#{#countryId}", nativeQuery = true)
//    public List<Location> findByCountryId(String countryId);
}