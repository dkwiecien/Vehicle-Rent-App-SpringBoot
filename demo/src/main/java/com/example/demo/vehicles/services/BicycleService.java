package com.example.demo.vehicles.services;

import com.example.demo.vehicles.dtos.BicycleRequest;
import com.example.demo.vehicles.dtos.BicycleResponse;
import com.example.demo.vehicles.entities.BicycleEntity;

import java.util.List;

public interface BicycleService {
    List<BicycleResponse> getBicycles();
    BicycleEntity addBicycle(BicycleRequest request);
}
