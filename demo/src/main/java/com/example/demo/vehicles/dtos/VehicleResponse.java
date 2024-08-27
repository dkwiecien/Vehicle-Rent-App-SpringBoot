package com.example.demo.vehicles.dtos;

public record VehicleResponse(
        double weight,
        String color,
        double price,
        boolean isRented
) {
}
