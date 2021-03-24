package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    
    public Integer createLocation(String regionId, String locationName);

    public Integer updateLocation(String locationId,String regionId, String locationName);

    public List<LocationDTO> getAllLocations();

    public LocationDTO getLocationById(String locationId);

    public List<LocationDTO> getLocationByName(String locationName);

    public void deleteLocation(String locationId);

    public List<LocationDTO> getLocationByRegion(String regionId);

//    public List<LocationDTO> getLocationByCountry(String countryName);
}
