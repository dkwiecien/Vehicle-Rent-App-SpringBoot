package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.MotorcycleRequest;
import com.example.demo.vehicles.dtos.MotorcycleResponse;
import com.example.demo.vehicles.entities.MotorcycleEntity;
import com.example.demo.vehicles.repositories.MotorcycleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MotorcycleServiceImplTest {

    @Mock
    MotorcycleRepository motorcycleRepository;
    @InjectMocks
    MotorcycleServiceImpl motorcycleService;

    @Test
    void getMotorcyclesTest() {
        MotorcycleEntity motorcycle = MotorcycleEntity.builder()
                .price(1000)
                .weight(100)
                .color("black")
                .engineDisplacement(0)
                .build();

        when(this.motorcycleRepository.findAll()).thenReturn(List.of(motorcycle));

        final List<MotorcycleResponse> motorcycles = this.motorcycleService.getMotorcycles();

        assertFalse(motorcycles.isEmpty());
    }

    @Test
    void addMotorcycleTest() {
        MotorcycleEntity motorcycle = MotorcycleEntity.builder()
                .price(1000)
                .weight(100)
                .color("black")
                .engineDisplacement(0)
                .build();

        MotorcycleRequest request = new MotorcycleRequest(100, "black", 1000, 0);

        when(this.motorcycleRepository.save(any(MotorcycleEntity.class))).thenReturn(motorcycle);

        final MotorcycleEntity result = this.motorcycleService.addMotorcycle(request);

        assertEquals(result, motorcycle);
    }
}