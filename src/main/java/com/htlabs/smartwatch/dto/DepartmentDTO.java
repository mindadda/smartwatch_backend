package com.htlabs.smartwatch.dto;

import com.htlabs.smartwatch.entity.ClientDetails;
import com.htlabs.smartwatch.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO {

    private String departmentId;

    private String departmentName;

    private ClientDTO clientDetails;

    private LocationDTO location;

    private Date createdAt;

    private Date updatedAt;
}
