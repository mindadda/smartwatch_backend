package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OperatorDetailsDTO {

    private String operatorId;

    private String operatorName;

    private Date createdAt;

    private Date updatedAt;
}
