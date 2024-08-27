package com.example.demo.vehicles.dtos;

public record BicycleDto(
        double weight,
        String color,
        double price,
        boolean isRented,
        boolean helperWheels
) {
}
