package com.example.demo.vehicles.dtos;

import java.util.UUID;

public record BicycleResponse(
        UUID uuid,
        double weight,
        String color,
        double price,
        boolean helperWheels
) {
}
