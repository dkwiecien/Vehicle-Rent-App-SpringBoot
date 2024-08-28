package com.example.demo.vehicles.services;

import com.example.demo.vehicles.dtos.MotorcycleRequest;
import com.example.demo.vehicles.dtos.MotorcycleResponse;
import com.example.demo.vehicles.entities.MotorcycleEntity;

import java.util.List;

public interface MotorcycleService {
    List<MotorcycleResponse> getMotorcycles();
    MotorcycleEntity addMotorcycle(MotorcycleRequest request);
}
