package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.dto.*;
import com.htlabs.smartwatch.service.CountryService;
import com.htlabs.smartwatch.service.LocationService;
import com.htlabs.smartwatch.service.OperatorService;
import com.htlabs.smartwatch.service.RegionService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import com.htlabs.smartwatch.utils.SuccessMessages;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@Validated
@Slf4j
@Component
public class CountryController extends BaseController{

    @Autowired
    private CountryService countryService;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "We can create a new Country.")
    @PostMapping(path = "/createCountry", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO createCountry(@RequestParam String countryName ) {
        Integer status = countryService.createCountry(countryName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.COUNTRY_CREATED, countryName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.COUNTRY_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value = "We can update details of the Country.")
    @PostMapping(path = "/updateCountry" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO updateCountry(@RequestParam String countryId ,
                                     @RequestParam String countryName){
        Integer status = countryService.updateCountry(countryId , countryName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.COUNTRY_UPDATED, countryName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.COUNTRY_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value = "Get details of Country")
    @GetMapping(path = "/findAllCountries", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }

    @ApiOperation(value = "We can find details of the Country.")
    @GetMapping(path = "/findCountryById", produces = { MediaType.APPLICATION_JSON_VALUE })
    public CountryDTO getCountryById(@RequestParam String countryId) {
        return countryService.getCountryById(countryId);
    }

    @ApiOperation(value = "We can find details of the Country.")
    @GetMapping(path = "/findCountryByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<CountryDTO> getCountryByName(@RequestParam String countryName) {
        return countryService.getCountryByName(countryName);
    }

    @ApiOperation(value = "Delete country countryId")
    @GetMapping(path = "/deleteCountry",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO deleteCountry(@RequestParam String countryId ){
        countryService.deleteCountry(countryId);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.COUNTRY_REMOVED));

    }

    @ApiOperation(value = "we can create operator")
    @PostMapping(path = "/createOperator",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO addOperator(@RequestParam String operatorName){
        Integer status = operatorService.createOperator(operatorName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.OPERATOR_CREATED, operatorName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.OPERATOR_EXIST);
        }
        return new ResponseDTO(status, message);

    }

    @ApiOperation(value="we can update operator")
    @PostMapping(path = "/updateOperator",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateOperator(@RequestParam String operatorId,
                                      @RequestParam String operatorName) {
        Integer status = operatorService.updateOperator(operatorId , operatorName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.OPERATOR_UPDATED_SUCCESSFULLY, operatorName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.OPERATOR_EXIST);
        }
        return new ResponseDTO(status, message);
    }


    @ApiOperation(value = "fetching all operators")
    @GetMapping(path = "/findAllOperators",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<OperatorDetailsDTO>getAllOperators() {
        return operatorService.getAllOperators();
    }

    @ApiOperation(value = "fetching operator by operatorId")
    @GetMapping(path = "/findOperatorById",produces = {MediaType.APPLICATION_JSON_VALUE})
    public OperatorDetailsDTO getOperatorById(@RequestParam String operatorId) {
        return operatorService.getOperatorById(operatorId);
    }

    @ApiOperation(value = "We can find details of the Operator.")
    @GetMapping(path = "/findOperatorByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<OperatorDetailsDTO> getOperatorByName(@RequestParam String operatorName) {
        return operatorService.getOperatorByName(operatorName);
    }

    @ApiOperation(value = "delete operator")
    @GetMapping(path = "/deleteOperator",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteOperator(@RequestParam String operatorId) {
        operatorService.deleteOperator(operatorId);
        return new ResponseDTO(HttpStatus.OK.value(),String.format(SuccessMessages.OPERATOR_REMOVED));
    }

    @ApiOperation(value = "we can create region")
    @PostMapping(path = "/createRegion",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO addRegion(@RequestParam String countryId,
                                 @RequestParam String regionName){
        Integer status = regionService.createRegion(countryId , regionName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.REGION_CREATED, regionName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.REGION_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value="we can update Region")
    @PostMapping(path = "/updateRegion",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateRegion(@RequestParam String regionId,
                                    @RequestParam String countryId,
                                    @RequestParam String regionName) {
        Integer status = regionService.updateRegion(regionId ,countryId, regionName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.REGION_UPDATED_SUCCESSFULLY, regionName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.REGION_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value = "fetching all Regions")
    @GetMapping(path = "/findAllRegions",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RegionDetailsDTO> getAllRegions() {
        return regionService.getAllRegions();
    }

    @ApiOperation(value = "fetching region by RegionId")
    @GetMapping(path = "/findRegionById",produces = {MediaType.APPLICATION_JSON_VALUE})
    public RegionDetailsDTO getRegionById(@RequestParam String regionId) {
        return regionService.getRegionById(regionId);
    }

    @ApiOperation(value = "fetching region by CountryId")
    @GetMapping(path = "/findRegionByCountry",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RegionDetailsDTO> getRegionByCountry(@RequestParam String countryId) {
        return regionService.getRegionByCountry(countryId);
    }

    @ApiOperation(value = "We can find details of the Region.")
    @GetMapping(path = "/findRegionByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<RegionDetailsDTO> getRegionByName(@RequestParam String regionName) {
        return regionService.getRegionByName(regionName);
    }

    @ApiOperation(value = "delete region")
    @GetMapping(path = "/deleteRegion",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteRegion(@RequestParam String regionId) {
        regionService.deleteRegion(regionId);
        return new ResponseDTO(HttpStatus.OK.value(),String.format(SuccessMessages.REGION_REMOVED));
    }

    @ApiOperation(value = "we can create location")
    @PostMapping(path = "/createLocation",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO addLocation(@RequestParam String regionId,
                                   @RequestParam String locationName){
        Integer status = locationService.createLocation(regionId , locationName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.LOCATION_CREATED, locationName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.LOCATION_EXIST);
        }
        return new ResponseDTO(status, message);

    }

    @ApiOperation(value="we can update Location")
    @PostMapping(path = "/updateLocation",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateLocation(@RequestParam String locationId,
                                      @RequestParam String regionId,
                                      @RequestParam String locationName) {
        Integer status = locationService.updateLocation(locationId ,regionId, locationName);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.LOCATION_UPDATED_SUCCESSFULLY, locationName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.LOCATION_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value = "fetching all Locations")
    @GetMapping(path = "/findAllLocations",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<LocationDTO> getAllLocation() {
        return locationService.getAllLocations();
    }

    @ApiOperation(value = "fetching location by RegionId")
    @GetMapping(path = "/findLocationByRegion",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<LocationDTO> getLocationByRegion(@RequestParam String regionId) {
        return locationService.getLocationByRegion(regionId);
    }

    @ApiOperation(value = "fetching location by LocationId")
    @GetMapping(path = "/findLocationById",produces = {MediaType.APPLICATION_JSON_VALUE})
    public LocationDTO getLocationById(@RequestParam String locationId) {
        return locationService.getLocationById(locationId);
    }

    @ApiOperation(value = "We can find details of the Location.")
    @GetMapping(path = "/findLocationByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<LocationDTO> getLocationByName(@RequestParam String locationName) {
        return locationService.getLocationByName(locationName);
    }

    @ApiOperation(value = "delete Location")
    @GetMapping(path = "/deleteLocation",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteLocation(@RequestParam String locationId) {
        locationService.deleteLocation(locationId);
        return new ResponseDTO(HttpStatus.OK.value(),String.format(SuccessMessages.LOCATION_REMOVED));
    }

}
