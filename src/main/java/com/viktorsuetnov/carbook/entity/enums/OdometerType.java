package com.viktorsuetnov.carbook.entity.enums;

public enum OdometerType {

    Kilometers("Километры"),
    Miles("Мили");

    private String title;

    OdometerType(String title) { this.title = title; }

    public String getTitle() { return title; }
}
