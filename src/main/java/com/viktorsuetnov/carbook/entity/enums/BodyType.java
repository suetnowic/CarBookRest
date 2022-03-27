package com.viktorsuetnov.carbook.entity.enums;

public enum BodyType {

    SEDAN("Седан"),
    HATCHBACK("Хэтчбек"),
    LIFTBACK("Лифтбек"),
    SUV("Внедорожник"),
    STATION_WAGON("Универсал"),
    COUPE("Купе"),
    MINIVAN("Минивен"),
    PICKUP("Пикап"),
    LIMOUSINE("Лимузин"),
    VAN("Фургон"),
    CABRIOLET("Кабриолет");

    private String title;

    BodyType(String title) {this.title = title; }

    public String getTitle() { return title; }
}
