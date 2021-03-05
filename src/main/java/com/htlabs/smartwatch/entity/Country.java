package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "country")
public class Country extends AuditEntity{

    @Id
    @Column(name = "country_id")
    private String countryId;

    @Column(name = "country_name")
    private String countryName;

    public Country(String countryId, String countryName){
        super();
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Country() {

    }
}
