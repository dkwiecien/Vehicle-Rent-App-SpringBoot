package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.BicycleRequest;
import com.example.demo.vehicles.dtos.BicycleResponse;
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
    public List<BicycleResponse> getBicycles() {
        return this.bicycleRepository.findAll().stream().map(bicycleEntity -> new BicycleResponse(
                bicycleEntity.getId(),
                bicycleEntity.getWeight(),
                bicycleEntity.getColor(),
                bicycleEntity.getPrice(),
                bicycleEntity.isHelperWheels()
        )).toList();
    }

    @Override
    public BicycleEntity addBicycle(BicycleRequest request) {
        BicycleEntity newBicycle = BicycleEntity.builder()
                .price(request.price())
                .color(request.color())
                .weight(request.weight())
                .helperWheels(request.helperWheels())
                .build();

        return this.bicycleRepository.save(newBicycle);
    }
}
