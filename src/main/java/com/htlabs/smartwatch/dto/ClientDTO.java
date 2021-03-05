package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private String clientId;

    private String clientName;

    private String clientPhone;

    private String clientAddress;

    private Date createdAt;

    private Date updatedAt;


}
