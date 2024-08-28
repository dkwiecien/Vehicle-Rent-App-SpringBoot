package com.example.demo.vehicles.dtos;

import java.util.UUID;

public record VehicleResponse(
        UUID id,
        double weight,
        String color,
        double price
) {
}
