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
@Entity(name = "bicycles")
public class BicycleEntity extends VehicleEntity {

    private boolean helperWheels;

    public BicycleEntity(double weight, String color, double price, boolean isRented, boolean helperWheels) {
        super(weight, color, price, isRented);
        this.helperWheels = helperWheels;
    }
}
