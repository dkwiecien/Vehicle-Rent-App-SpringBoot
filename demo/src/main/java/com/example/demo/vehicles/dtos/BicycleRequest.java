package com.example.demo.vehicles.dtos;

public record BicycleRequest(
        double weight,
        String color,
        double price,
        boolean helperWheels
) {
}
