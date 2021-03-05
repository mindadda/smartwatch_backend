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
@Table(name = "operator")
public class OperatorDetails extends AuditEntity{

    @Id
    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "operator_name")
    private String operatorName;


    public OperatorDetails(String operatorId, String operatorName){
        super();
        this.operatorId=operatorId;
        this.operatorName=operatorName;
    }

    public OperatorDetails(){

    }

}
