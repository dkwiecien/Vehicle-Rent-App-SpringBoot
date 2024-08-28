package com.example.demo.vehicles.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
public class CarEntity extends VehicleEntity{

    private int numberOfSeats;

    @Builder
    public CarEntity(UUID id, double weight, String color, double price , int numberOfSeats) {
        super(id, weight, color, price);
        this.numberOfSeats = numberOfSeats;
    }
}
