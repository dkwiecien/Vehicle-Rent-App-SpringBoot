package com.example.demo.rents.dtos;

import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.vehicles.dtos.VehicleResponse;

import java.util.UUID;

public record RentResponse(
        UUID id,
        double price,
        boolean isArchive,
        ClientResponse client,
        VehicleResponse vehicle
) {
}
