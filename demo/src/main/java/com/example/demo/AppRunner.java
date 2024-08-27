package com.example.demo;

import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.entities.CarEntity;
import com.example.demo.vehicles.entities.MotorcycleEntity;
import com.example.demo.vehicles.entities.VehicleEntity;
import com.example.demo.vehicles.repositories.BicycleRepository;
import com.example.demo.vehicles.repositories.CarsRepository;
import com.example.demo.vehicles.repositories.MotorcycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final CarsRepository carsRepository;
    private final BicycleRepository bicycleRepository;
    private final MotorcycleRepository motorcycleRepository;

    @Override
    public void run(String... args) throws Exception {
        CarEntity car = new CarEntity(500, 2500, "black", false, 5);
        this.carsRepository.save(car);

        BicycleEntity bicycle = new BicycleEntity(20, "silver", 100, false, false);
        this.bicycleRepository.save(bicycle);

        MotorcycleEntity motorcycle = new MotorcycleEntity(80, "blue", 300.5, false, 0);
        this.motorcycleRepository.save(motorcycle);
    }
}
