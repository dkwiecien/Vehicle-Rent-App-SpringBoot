package com.example.demo.vehicles.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
public class CarEntity extends VehicleEntity{

    private int numberOfSeats;

    public CarEntity(double weight, double price, String color, boolean isRented, int numberOfSeats) {
        super(weight, color, price, isRented);
        this.numberOfSeats = numberOfSeats;
    }
}
