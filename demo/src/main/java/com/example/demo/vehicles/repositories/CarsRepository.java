package com.example.demo.vehicles.repositories;

import com.example.demo.vehicles.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarsRepository extends JpaRepository<CarEntity, UUID> {
}
