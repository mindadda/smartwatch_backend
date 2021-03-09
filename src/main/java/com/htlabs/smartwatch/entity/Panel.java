package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "panel")
public class Panel extends AuditEntity{

    @Id
    @Column(name = "panel_id")
    private String panelId;

    @Column(name = "panel_name")
    private String panelName;

    @Column(name = "row_no")
    private Integer rowNo;

    @Column(name = "column_no")
    private Integer columnNo;

    @Column(name = "current_value")
    private String currentValue;

    @Column(name = "current_time_value")
    private String currentTime;

    @Column(name = "current_updated_time")
    private String currentUpdatedTime;

    @Column(name = "previous_value")
    private String previousValue;

    @Column(name = "previous_time_value")
    private String previousTime;

    @Column(name = "previous_updated_time")
    private String previousUpdatedTime;

    @Column(name = "sensor_id")
    private String sensorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenDetails screen;

    public Panel(String panelId, String panelName, Integer rowNo, Integer columnNo, String currentValue,
                 String currentTime,String currentUpdatedTime, String previousValue, String previousTime,
                 String previousUpdatedTime, String sensorId){
        super();
        this.panelId = panelId;
        this.panelName = panelName;
        this.rowNo = rowNo;
        this.columnNo = columnNo;
        this.currentValue = currentValue;
        this.currentTime = currentTime;
        this.currentUpdatedTime = currentUpdatedTime;
        this.previousValue = previousValue;
        this.previousTime = previousTime;
        this.previousUpdatedTime = previousUpdatedTime;
        this.sensorId = sensorId;
    }

    public Panel(){

    }

}
