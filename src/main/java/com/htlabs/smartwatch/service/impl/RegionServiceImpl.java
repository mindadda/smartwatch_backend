package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.RegionDetailsDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.RegionDetails;
import com.htlabs.smartwatch.entity.converter.CountryConverter;
import com.htlabs.smartwatch.entity.converter.RegionConverter;
import com.htlabs.smartwatch.repository.CountryRepository;
import com.htlabs.smartwatch.repository.RegionDetailRepository;
import com.htlabs.smartwatch.service.RegionService;
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
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDetailRepository regionDetailRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Override
    public void createRegion(String countryId, String regionName) {
//        String countryId = countryRepository.findByCountryName(countryName);
        Country country = countryRepository.findById(countryId).orElse(null);
        if(country == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_COUNTRY);
        }

        String regionname = regionDetailRepository.findRegionName(regionName);
        if (regionname == null){
            log.info("Creating Region:  {}", regionName);
            String regionId = UUID.randomUUID().toString();
            RegionDetails regionDetails = new RegionDetails(regionId, regionName);
            regionDetails.setCountry(country);
            regionDetails.setCreatedAt(new Date());
            regionDetails.setUpdatedAt(new Date());
            regionDetailRepository.save(regionDetails);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.REGION_EXIST);
        }
    }

    @Override
    public void updateRegion(String regionId, String countryId, String regionName) {
//        String countryId = countryRepository.findByCountryName(countryName);
        Country country = countryRepository.findById(countryId).orElse(null);
        if(country == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_COUNTRY);
        }

        RegionDetails regionDetails = regionDetailRepository.findById(regionId).orElse(null);
        if (regionDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_REGION);
        }
        else {
            String regionname = regionDetailRepository.findRegionName(regionName);
            if (regionname == null){
                log.info("Updating Region:  {}", regionName);
                regionDetails.setRegionName(regionName);
                regionDetails.setCountry(country);
                regionDetails.setCreatedAt(new Date());
                regionDetails.setUpdatedAt(new Date());
                regionDetailRepository.save(regionDetails);
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.REGION_EXIST);
            }

        }
    }

    @Override
    public List<RegionDetailsDTO> getAllRegions() {
        log.info("Retrieving all the Regions.");
        List<RegionDetails> regionDetails = regionDetailRepository.findAll();
        return RegionConverter.getRegionDTOListFromEntityList(regionDetails);
    }

    @Override
    public RegionDetailsDTO getRegionById(String regionId) {
        log.info("Retrieving the Regions Details : {}", regionId);
        RegionDetails regionDetails = regionDetailRepository.findById(regionId).orElse(null);
        return RegionConverter.getRegionDtoFromEntity(regionDetails);
    }

    @Override
    public List<RegionDetailsDTO> getRegionByName(String regionName) {
        List<RegionDetails> regionDetails = regionDetailRepository.findByName(regionName);
        if (regionDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_REGION);
        }
        return RegionConverter.getRegionDTOListFromEntityList(regionDetails);
    }

    @Override
    public void deleteRegion(String regionId) {
        log.info("Deleting Region : {}", regionId);
        RegionDetails regionDetails = regionDetailRepository.findById(regionId).orElse(null);
        if (regionDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_REGION);
        }
        regionDetailRepository.deleteRegion(regionId);
    }

    @Override
    public List<RegionDetailsDTO> getRegionByCountry(String countryId) {
        log.info("Retrieving Region by CountryId ");
//        String countryId = countryRepository.findByCountryName(countryName);
        Country country = countryRepository.findById(countryId).orElse(null);
        if(country == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_COUNTRY);
        }

        List<RegionDetails> regionDetails = regionDetailRepository.findByCountryId(countryId);
        return RegionConverter.getRegionDTOListFromEntityList(regionDetails);
    }
}
