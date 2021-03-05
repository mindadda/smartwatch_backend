package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT * FROM country WHERE country_name LIKE %:countryName%", nativeQuery = true)
    public List<Country> findByName(String countryName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM country where country_id = :#{#countryId}", nativeQuery = true)
    public void deleteCountry(String countryId);

    @Query(value = "SELECT country_name FROM country WHERE country_name= :#{#countryName}", nativeQuery = true)
    public String findCountryName(String countryName);

    @Query(value = "SELECT country_id FROM country WHERE country_name= :#{#countryName}", nativeQuery = true)
    public String findByCountryName(String countryName);
}
