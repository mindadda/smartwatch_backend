package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "client_location_mapping")
public class ClientLocation extends AuditEntity{

    @Id
    @Column(name = "client_location_id")
    private String clientLocationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientDetails clientDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    public ClientLocation(String clientLocationId){
        super();
        this.clientLocationId = clientLocationId;
    }

    public ClientLocation(){

    }
}
