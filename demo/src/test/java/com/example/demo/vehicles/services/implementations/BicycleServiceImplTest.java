package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.BicycleRequest;
import com.example.demo.vehicles.dtos.BicycleResponse;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.repositories.BicycleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BicycleServiceImplTest {

    @Mock
    BicycleRepository bicycleRepository;
    @InjectMocks
    BicycleServiceImpl bicycleService;

    @Test
    void getBicyclesTest() {
        BicycleEntity bicycle = BicycleEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        when(this.bicycleRepository.findAll()).thenReturn(List.of(bicycle));

        final List<BicycleResponse> bicycles = this.bicycleService.getBicycles();

        assertFalse(bicycles.isEmpty());
    }

    @Test
    void addBicycleTest() {
        BicycleEntity bicycle = BicycleEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        BicycleRequest request = new BicycleRequest(100, "black", 1000, false);

        when(this.bicycleRepository.save(any(BicycleEntity.class))).thenReturn(bicycle);

        final BicycleEntity result = this.bicycleService.addBicycle(request);

        assertEquals(result, bicycle);
    }
}