package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.BicycleDto;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.repositories.BicycleRepository;
import com.example.demo.vehicles.services.BicycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BicycleServiceImpl implements BicycleService {

    private final BicycleRepository bicycleRepository;
    @Override
    public List<BicycleDto> getBicycles() {
        return this.bicycleRepository.findAll().stream().map(bicycleEntity -> new BicycleDto(
                bicycleEntity.getWeight(),
                bicycleEntity.getColor(),
                bicycleEntity.getPrice(),
                bicycleEntity.isHelperWheels()
        )).toList();
    }

    @Override
    public BicycleEntity save(BicycleDto request) {
        BicycleEntity newBicycle = BicycleEntity.builder()
                .price(request.price())
                .color(request.color())
                .weight(request.weight())
                .helperWheels(request.helperWheels())
                .build();

        return this.bicycleRepository.save(newBicycle);
    }
}
