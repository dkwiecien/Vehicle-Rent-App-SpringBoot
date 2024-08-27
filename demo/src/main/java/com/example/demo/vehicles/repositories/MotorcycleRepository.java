package com.example.demo.vehicles.repositories;

import com.example.demo.vehicles.entities.MotorcycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MotorcycleRepository extends JpaRepository<MotorcycleEntity, UUID> {
}
