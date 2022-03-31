package com.viktorsuetnov.carbook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carBrand;
    private String carModel;
    private String carGeneration;

    @JsonFormat(pattern = "yyyy")
    private Date yearOfIssue;

    private String carTransmission;
    private String carEngineType;
    private String carBodyType;
    private Double carEngineCapacity;
    private Double carEnginePower;
    private String carOdometerType;
    private String carColor;
    private Double currentMileage;
    private String vrp;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "car", orphanRemoval = true)
    private List<Event> events = new ArrayList<>();
}
