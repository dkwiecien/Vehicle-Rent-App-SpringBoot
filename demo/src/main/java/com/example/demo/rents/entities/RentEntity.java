package com.example.demo.rents.entities;

import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.vehicles.entities.VehicleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rents")
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double price;
    private boolean isArchive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public RentEntity(double price, boolean isArchive, ClientEntity client, VehicleEntity vehicle) {
        this.price = price;
        this.isArchive = isArchive;
        this.client = client;
        this.vehicle = vehicle;
    }
}
