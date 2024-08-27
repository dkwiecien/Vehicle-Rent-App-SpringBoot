package com.example.demo.vehicles.repositories;

import com.example.demo.vehicles.entities.BicycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BicycleRepository extends JpaRepository<BicycleEntity, UUID> {
}
