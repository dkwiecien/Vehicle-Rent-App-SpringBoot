package com.example.demo.vehicles.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "motorcycles")
public class MotorcycleEntity extends VehicleEntity {

    private int engineDisplacement;

    @Builder
    public MotorcycleEntity(UUID id, double weight, String color, double price, int engineDisplacement) {
        super(id, weight, color, price);
        this.engineDisplacement = engineDisplacement;
    }
}
