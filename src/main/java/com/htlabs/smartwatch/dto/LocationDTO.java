package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LocationDTO {

    private String locationId;

    private String locationName;

    private RegionDetailsDTO region;

    private Date createdAt;

    private Date updatedAt;
}
