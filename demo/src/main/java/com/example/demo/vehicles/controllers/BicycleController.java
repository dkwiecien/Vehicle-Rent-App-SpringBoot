package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.BicycleDto;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<BicycleDto>> getBicycles() {
        return ResponseEntity.ok(this.bicycleService.getBicycles());
    }

    @PostMapping("/save")
    public BicycleEntity save(@RequestBody BicycleDto request) {
        return this.bicycleService.save(request);
    }
}
