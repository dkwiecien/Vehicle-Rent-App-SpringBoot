package com.example.demo.vehicles.dtos;

import java.util.UUID;

public record VehicleRequest(
        double weight,
        String color,
        double price
) {
}
