package com.viktorsuetnov.carbook.entity;

import com.viktorsuetnov.carbook.entity.enums.BodyType;
import com.viktorsuetnov.carbook.entity.enums.EngineType;
import com.viktorsuetnov.carbook.entity.enums.OdometerType;
import com.viktorsuetnov.carbook.entity.enums.TransmissionType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Car {

    private Long id;
    private String carBrand;
    private String carModel;
    private String carGeneration;
    private Date yearOfIssue;
    private TransmissionType carTransmission;
    private EngineType carEngineType;
    private BodyType carBodyType;
    private Double carEngineCapacity;
    private Double carEnginePower;
    private OdometerType carOdometerType;
    private String carColor;
    private Double currentMileage;
    private String vrp;

    private User owner;

    private List<Event> events = new ArrayList<Event>();
}
