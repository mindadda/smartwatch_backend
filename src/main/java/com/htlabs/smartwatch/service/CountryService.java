package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.CountryDTO;

import java.util.List;

public interface CountryService {

    public void createCountry(String countryName);

    public List<CountryDTO> getAllCountries();

    public CountryDTO getCountryById(String countryId);

    public void updateCountry(String countryId, String countryName);

    public List<CountryDTO> getCountryByName(String countryName);

    public void deleteCountry(String countryId);
}