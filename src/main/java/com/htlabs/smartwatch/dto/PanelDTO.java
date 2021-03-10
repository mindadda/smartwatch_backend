package com.htlabs.smartwatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PanelDTO {

    private String panelId;

    private String panelName;

    private Integer rowNo;

    private Integer columnNo;

    private String currentValue;

    private String currentTime;

    private String currentUpdatedTime;

    private String previousValue;

    private String previousTime;

    private String previousUpdatedTime;

    private String sensorId;

    private ScreenDTO screen;

    private Date createdAt;

    private Date updatedAt;

}
