package com.htlabs.smartwatch.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name="message")
public class Model {

    @Id
    @Column(name = "message_id")
    private String panelId;

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "sensor_value")
    private String sensorValue;

    @Column(name = "created_at")
    private String currentTime;

    @Column(name = "updated_at")
    private String updatedTime;


    public Model() {

    }

    public Model(String panelId , String sensorId , String sensorValue , String currentTime , String updatedTime) {
        this.panelId = panelId;
        this.sensorId = sensorId;
        this.sensorValue = sensorValue;
        this.currentTime = currentTime;
        this.updatedTime = updatedTime;
    }

//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

    @Override
    public String toString() {
        return String.format("Model [message=%s]", sensorValue);
    }

}
