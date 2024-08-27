package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.MotorcycleDto;
import com.example.demo.vehicles.entities.MotorcycleEntity;
import com.example.demo.vehicles.services.MotorcycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycles")
@RequiredArgsConstructor
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    @GetMapping("/getAll")
    public ResponseEntity<List<MotorcycleDto>> getMotorcycles() {
        return ResponseEntity.ok(this.motorcycleService.getMotorcycles());
    }

    @PostMapping("/save")
    public MotorcycleEntity save(@RequestBody MotorcycleDto request) {
        return this.motorcycleService.save(request);
    }
}
