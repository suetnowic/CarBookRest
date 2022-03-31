package com.viktorsuetnov.carbook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CarDTO {

    private Long id;
    private String carBrand;
    private String carModel;
//    private String carGeneration;

    @JsonFormat(pattern = "yyyy")
    private Date yearOfIssue;

//    private String carTransmission;
//    private String carEngineType;
//    private String carBodyType;
    private Double carEngineCapacity;
//    private Double carEnginePower;
//    private String carOdometerType;
//    private String carColor;
    private String vrp;

}
