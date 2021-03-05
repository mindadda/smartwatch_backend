package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    
    public void createLocation(String regionName, String locationName);

    public void updateLocation(String locationId,String regionName, String locationName);

    public List<LocationDTO> getAllLocations();

    public LocationDTO getLocationById(String locationId);

    public List<LocationDTO> getLocationByName(String locationName);

    public void deleteLocation(String locationId);

    public List<LocationDTO> getLocationByRegion(String regionName);

//    public List<LocationDTO> getLocationByCountry(String countryName);
}
