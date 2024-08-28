package com.example.demo.vehicles.services;

import com.example.demo.vehicles.dtos.CarRequest;
import com.example.demo.vehicles.dtos.CarResponse;
import com.example.demo.vehicles.entities.CarEntity;

import java.util.List;

public interface CarService {
    List<CarResponse> getCars();
    CarEntity addCar(CarRequest request);
}
