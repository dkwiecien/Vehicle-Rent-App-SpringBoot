package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.MotorcycleRequest;
import com.example.demo.vehicles.dtos.MotorcycleResponse;
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
    public ResponseEntity<List<MotorcycleResponse>> getMotorcycles() {
        return ResponseEntity.ok(this.motorcycleService.getMotorcycles());
    }

    @PostMapping("/save")
    public MotorcycleEntity addMotorcycle(@RequestBody MotorcycleRequest request) {
        return this.motorcycleService.addMotorcycle(request);
    }
}
