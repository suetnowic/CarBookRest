package com.viktorsuetnov.carbook.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CarDTO {

    private Long id;
    private String carBrand;
    private String carModel;
    private String carGeneration;
    private String yearOfIssue;
    private String carTransmission;
    private String carEngineType;
    private String carBodyType;
    private Double carEngineCapacity;
    private Double carEnginePower;
    private String carOdometerType;
    private String carColor;
    private String vrp;

}
