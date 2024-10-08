package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.MotorcycleRequest;
import com.example.demo.vehicles.dtos.MotorcycleResponse;
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
    public List<MotorcycleResponse> getMotorcycles() {
        return this.motorcycleRepository.findAll().stream().map(
                motorcycleEntity -> new MotorcycleResponse(
                        motorcycleEntity.getId(),
                        motorcycleEntity.getWeight(),
                        motorcycleEntity.getColor(),
                        motorcycleEntity.getPrice(),
                        motorcycleEntity.getEngineDisplacement()
                )
        ).toList();
    }

    @Override
    public MotorcycleEntity addMotorcycle(MotorcycleRequest request) {
        MotorcycleEntity newMotorcycle = MotorcycleEntity.builder()
                .price(request.price())
                .weight(request.weight())
                .color(request.color())
                .engineDisplacement(request.engineDisplacement())
                .build();

        return this.motorcycleRepository.save(newMotorcycle);
    }
}
