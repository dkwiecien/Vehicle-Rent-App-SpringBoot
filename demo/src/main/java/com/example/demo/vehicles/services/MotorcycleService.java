package com.example.demo.vehicles.services;

import com.example.demo.vehicles.dtos.MotorcycleDto;
import com.example.demo.vehicles.entities.MotorcycleEntity;

import java.util.List;

public interface MotorcycleService {
    List<MotorcycleDto> getMotorcycles();
    MotorcycleEntity save(MotorcycleDto request);
}
