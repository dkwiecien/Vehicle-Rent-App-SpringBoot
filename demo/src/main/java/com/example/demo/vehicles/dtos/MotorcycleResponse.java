package com.example.demo.vehicles.dtos;

import java.util.UUID;

public record MotorcycleResponse(
        UUID id,
        double weight,
        String color,
        double price,
        int engineDisplacement
) {
}
