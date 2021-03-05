package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RegionDetailsDTO {

    private String regionId;

    private String regionName;

    private CountryDTO country;

    private Date createdAt;

    private Date updatedAt;

}
