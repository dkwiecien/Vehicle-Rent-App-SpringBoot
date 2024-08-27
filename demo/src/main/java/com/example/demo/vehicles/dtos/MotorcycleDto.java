package com.example.demo.vehicles.dtos;

public record MotorcycleDto(
        double weight,
        String color,
        double price,
        boolean isRented,
        int engineDisplacement
) {
}
