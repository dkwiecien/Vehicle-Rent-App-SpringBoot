package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.BicycleRequest;
import com.example.demo.vehicles.dtos.BicycleResponse;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.services.BicycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicycles")
@RequiredArgsConstructor
public class BicycleController {

    private final BicycleService bicycleService;

    @GetMapping
    public ResponseEntity<List<BicycleResponse>> getBicycles() {
        return ResponseEntity.ok(this.bicycleService.getBicycles());
    }

    @PostMapping
    public BicycleEntity addBicycle(@RequestBody BicycleRequest request) {
        return this.bicycleService.addBicycle(request);
    }
}
