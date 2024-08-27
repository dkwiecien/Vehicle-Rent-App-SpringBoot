package com.example.demo.vehicles.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "motorcycles")
public class MotorcycleEntity extends VehicleEntity {

    private int engineDisplacement;

    public MotorcycleEntity(double weight, String color, double price, boolean isRented, int engineDisplacement) {
        super(weight, color, price, isRented);
        this.engineDisplacement = engineDisplacement;
    }
}
