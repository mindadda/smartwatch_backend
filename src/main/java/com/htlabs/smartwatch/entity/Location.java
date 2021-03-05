package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "location")
public class Location extends AuditEntity{

    @Id
    @Column(name = "location_id")
    private String locationId;

    @Column(name = "location_name")
    private String locationName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private RegionDetails region;

    public Location(String locationId,String locationName){
        super();
        this.locationId=locationId;
        this.locationName=locationName;

    }

    public Location(){

    }
}
