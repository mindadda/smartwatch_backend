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
public class ClientLocationDTO {

    private String clientLocationId;

    private ClientDTO clientDetails;

    private LocationDTO location;

    private Date createdAt;

    private Date updatedAt;

}
