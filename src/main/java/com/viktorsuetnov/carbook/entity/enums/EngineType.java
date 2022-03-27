package com.viktorsuetnov.carbook.entity.enums;

public enum EngineType {

    PETROL("Бензин"),
    DIESEL("Дизель"),
    GAS("Газ"),
    HYBRID("Гибрид"),
    ELECTRECITY("Электричество");

    private String title;

    EngineType(String title){
        this.title = title;
    }

    public String getTitle() { return title; }
}
