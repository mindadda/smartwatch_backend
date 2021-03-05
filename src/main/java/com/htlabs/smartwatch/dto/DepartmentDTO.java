package com.htlabs.smartwatch.dto;

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

    private ClientLocationDTO clientLocation;

    private Date createdAt;

    private Date updatedAt;
}
