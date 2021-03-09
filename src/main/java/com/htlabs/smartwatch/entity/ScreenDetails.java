package com.htlabs.smartwatch.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "screen")
public class ScreenDetails extends AuditEntity{

    @Id
    @Column(name = "screen_id")
    private String screenId;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "row_count")
    private Integer rowCount;

    @Column(name = "column_count")
    private Integer columnCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public ScreenDetails(String screenId , String screenName , Integer rowCount , Integer columnCount){
        super();
        this.screenId = screenId;
        this.screenName = screenName;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public ScreenDetails(){

    }

}
