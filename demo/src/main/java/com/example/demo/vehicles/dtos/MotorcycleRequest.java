package com.example.demo.vehicles.dtos;

import java.util.UUID;

public record MotorcycleRequest(
        double weight,
        String color,
        double price,
        int engineDisplacement
) {
}
