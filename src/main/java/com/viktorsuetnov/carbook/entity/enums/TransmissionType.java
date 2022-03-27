package com.viktorsuetnov.carbook.entity.enums;

public enum  TransmissionType {

    AUTOMATIC("Автоматическая"),
    MANUAL("Механическая"),
    ROBOT("Робот"),
    VARIABLE("Вариатор");

    private String title;

    TransmissionType(String title) { this.title = title; }

    public String getTitle() { return title; }
}
