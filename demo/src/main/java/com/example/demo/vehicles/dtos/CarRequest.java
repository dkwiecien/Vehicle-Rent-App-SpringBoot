package com.example.demo.vehicles.dtos;

public record CarRequest(
        double weight,
        String color,
        double price,
        int numberOfSeats
) {
}
