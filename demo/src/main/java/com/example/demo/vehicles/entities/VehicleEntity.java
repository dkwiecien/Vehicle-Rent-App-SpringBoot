package com.example.demo.vehicles.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double weight;
    private String color;
    private double price;
    private boolean isRented;

    public VehicleEntity(double weight, String color, double price, boolean isRented) {
        this.weight = weight;
        this.color = color;
        this.price = price;
        this.isRented = isRented;
    }
}
