package com.example.demo.vehicles.services;

import com.example.demo.vehicles.dtos.BicycleDto;
import com.example.demo.vehicles.entities.BicycleEntity;

import java.util.List;

public interface BicycleService {
    List<BicycleDto> getBicycles();
    BicycleEntity save(BicycleDto request);
}
