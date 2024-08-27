package com.example.demo.vehicles.services;

import com.example.demo.vehicles.dtos.CarDto;
import com.example.demo.vehicles.entities.CarEntity;

import java.util.List;

public interface CarService {
    List<CarDto> getAll();
    CarEntity save(CarDto request);
}
