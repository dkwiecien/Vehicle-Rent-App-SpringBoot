package com.example.demo.rents.dtos;

import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.vehicles.dtos.VehicleRequest;
import com.example.demo.vehicles.dtos.VehicleResponse;

import java.util.UUID;

public record RentRequest(
        UUID id,
        double price,
        boolean isArchive,
        ClientRequest client,
        VehicleRequest vehicle
) {
}
