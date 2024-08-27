package com.example.demo.vehicles.dtos;

public record CarDto(
        double weight,
        String color,
        double price,
        boolean isRented,
        int numberOfSeats
) {
}
