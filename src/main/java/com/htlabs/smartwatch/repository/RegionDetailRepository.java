package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.RegionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RegionDetailRepository extends JpaRepository<RegionDetails,String> {

    @Query(value = "SELECT region_name FROM region WHERE region_name= :#{#regionName}", nativeQuery = true)
    public String findRegionName(String regionName);

    @Query(value = "SELECT * FROM region WHERE region_name LIKE %:regionName%", nativeQuery = true)
    public List<RegionDetails> findByName(String regionName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM region where region_id = :#{#regionId}", nativeQuery = true)
    public void deleteRegion(String regionId);

    @Query(value = "SELECT * FROM region WHERE country_id= :#{#countryId}", nativeQuery = true)
    public List<RegionDetails> findByCountryId(String countryId);

    @Query(value = "SELECT region_id FROM region WHERE region_name= :#{#regionName}", nativeQuery = true)
    public String findByRegionName(String regionName);
}
