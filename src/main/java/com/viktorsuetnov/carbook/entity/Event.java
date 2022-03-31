package com.viktorsuetnov.carbook.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEvent;
    private String operationTitle;
    private String consumables;
    private Double qty;
    private Double price;
    private Double odometerReading;

    @Column(columnDefinition = "text")
    private String note;
}
