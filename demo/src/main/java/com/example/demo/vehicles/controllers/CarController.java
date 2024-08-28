package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.CarRequest;
import com.example.demo.vehicles.dtos.CarResponse;
import com.example.demo.vehicles.entities.CarEntity;
import com.example.demo.vehicles.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CarResponse>> getCars() {
        return ResponseEntity.ok(this.carService.getCars());
    }

    @PostMapping("/save")
    public CarEntity addCar(@RequestBody CarRequest request) {
        return this.carService.addCar(request);
    }
}
