package com.example.demo.vehicles.dtos;

import java.util.UUID;

public record VehicleRequest(
        UUID id,
        double weight,
        String color,
        double price
) {
}
