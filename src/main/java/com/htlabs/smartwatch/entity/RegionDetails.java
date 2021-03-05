package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "region")
public class RegionDetails extends AuditEntity{

    @Id
    @Column(name = "region_id")
    private String regionId;

    @Column(name = "region_name")
    private String regionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public RegionDetails(String regionId,String regionName){
        super();
        this.regionId=regionId;
        this.regionName=regionName;

    }

    public RegionDetails(){

    }

}
