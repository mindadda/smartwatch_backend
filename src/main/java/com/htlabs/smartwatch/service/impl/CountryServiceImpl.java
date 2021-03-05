package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.converter.CountryConverter;
import com.htlabs.smartwatch.repository.CountryRepository;
import com.htlabs.smartwatch.service.CountryService;
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
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void createCountry(String countryName) {
        String countryname = countryRepository.findCountryName(countryName);
        if (countryname == null){
            log.info("Creating Country:  {}", countryName);
            String countryId = UUID.randomUUID().toString();
            Country country = new Country(countryId, countryName);
            country.setCreatedAt(new Date());
            country.setUpdatedAt(new Date());
            countryRepository.save(country);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.COUNTRY_EXIST);
        }
    }

    @Override
    public void updateCountry(String countryId, String countryName) {
        Country country = countryRepository.findById(countryId).orElse(null);
        if (country == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_COUNTRY);
        }
        else {
            String countryname = countryRepository.findCountryName(countryName);
            if (countryname == null){
                log.info("Updating Country:  {}", countryName);
                country.setCountryName(countryName);
                country.setUpdatedAt(new Date());
                countryRepository.save(country);
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.COUNTRY_EXIST);
            }

        }
    }

    @Override
    public List<CountryDTO> getCountryByName(String countryName) {
        List<Country> country = countryRepository.findByName(countryName);
        if (country == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_COUNTRY);
        }
        return CountryConverter.getCountryDTOListFromEntityList(country);
    }

    @Override
    public void deleteCountry(String countryId) {
        log.info("Deleting country : {}",countryId);
        Country country = countryRepository.findById(countryId).orElse(null);
        if (country == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_COUNTRY);
        }
        countryRepository.deleteCountry(countryId);
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        log.info("Retrieving all the Countries.");
        List<Country> countries = countryRepository.findAll();
        return CountryConverter.getCountryDTOListFromEntityList(countries);
    }

    @Override
    public CountryDTO getCountryById(String countryId) {
        log.info("Retrieving the Country Details : {}", countryId);
        Country country = countryRepository.findById(countryId).orElse(null);
        return CountryConverter.getCountryDtoFromEntity(country);
    }

}