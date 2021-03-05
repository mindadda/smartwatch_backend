package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CountryDTO {

    private String countryId;

    private String countryName;

    private Date createdAt;

    private Date updatedAt;
}
