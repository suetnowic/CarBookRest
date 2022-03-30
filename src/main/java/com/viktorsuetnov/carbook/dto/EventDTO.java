package com.viktorsuetnov.carbook.dto;

import com.viktorsuetnov.carbook.entity.Car;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Date;

@Data
public class EventDTO {

    private Long id;
    private Car car;
    private Date dateEvent;
    private String operationTitle;
    private String consumables;
    private Double qty;
    private Double price;
    private Double odometerReading;

    @Column(columnDefinition = "text")
    private String note;
}
