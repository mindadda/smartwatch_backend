package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ScreenDTO {

    private String screenId;

    private String screenName;

    private DepartmentDTO department;

    private Date createdAt;

    private Date updatedAt;
}
