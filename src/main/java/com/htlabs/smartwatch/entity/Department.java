package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "department")
public class Department extends AuditEntity{

    @Id
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_location_id")
    private ClientLocation clientLocation;

    public Department(String departmentId , String departmentName){
        super();
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Department(){

    }

}
