package com.example.demo.vehicles.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bicycles")
public class BicycleEntity extends VehicleEntity {

    private boolean helperWheels;

    @Builder
    public BicycleEntity(UUID id, double weight, String color, double price, boolean helperWheels) {
        super(id, weight, color, price);
        this.helperWheels = helperWheels;
    }
}
