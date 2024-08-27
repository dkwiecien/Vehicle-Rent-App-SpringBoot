package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.MotorcycleDto;
import com.example.demo.vehicles.entities.MotorcycleEntity;
import com.example.demo.vehicles.repositories.MotorcycleRepository;
import com.example.demo.vehicles.services.MotorcycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorcycleServiceImpl implements MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    @Override
    public List<MotorcycleDto> getMotorcycles() {
        return this.motorcycleRepository.findAll().stream().map(
                motorcycleEntity -> new MotorcycleDto(
                        motorcycleEntity.getWeight(),
                        motorcycleEntity.getColor(),
                        motorcycleEntity.getPrice(),
                        motorcycleEntity.isRented(),
                        motorcycleEntity.getEngineDisplacement()
                )
        ).toList();
    }

    @Override
    public MotorcycleEntity save(MotorcycleDto request) {
        MotorcycleEntity newMotorcycle = new MotorcycleEntity(
                request.weight(),
                request.color(),
                request.price(),
                request.isRented(),
                request.engineDisplacement()
        );
        return this.motorcycleRepository.save(newMotorcycle);
    }
}
