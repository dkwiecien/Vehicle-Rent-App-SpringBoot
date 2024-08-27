package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.CarDto;
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
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok(this.carService.getAll());
    }

    @PostMapping("/save")
    public CarEntity save(@RequestBody CarDto request) {
        return this.carService.save(request);
    }
}
