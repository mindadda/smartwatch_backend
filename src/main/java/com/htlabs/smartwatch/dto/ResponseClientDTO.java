package com.htlabs.smartwatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseClientDTO {

    private int status;

    private String message;

    private String clientName;
}
