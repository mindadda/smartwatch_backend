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
@Table(name = "client")
public class ClientDetails extends AuditEntity{

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "client_address")
    private String clientAddress;



    public ClientDetails(String clientId,String clientName,String clientPhone,String clientAddress){
        super();
        this.clientId=clientId;
        this.clientName=clientName;
        this.clientPhone=clientPhone;
        this.clientAddress=clientAddress;
    }

    public ClientDetails(){

    }


}
