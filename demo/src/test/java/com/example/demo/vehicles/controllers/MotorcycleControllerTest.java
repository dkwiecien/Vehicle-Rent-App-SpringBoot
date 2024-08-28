package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.MotorcycleRequest;
import com.example.demo.vehicles.dtos.MotorcycleResponse;
import com.example.demo.vehicles.entities.MotorcycleEntity;
import com.example.demo.vehicles.services.MotorcycleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MotorcycleControllerTest {

    @Mock
    MotorcycleService motorcycleService;
    @InjectMocks
    MotorcycleController motorcycleController;

    @Test
    void getMotorcycles() {
        MotorcycleResponse motorcycleResponse = new MotorcycleResponse(UUID.randomUUID(), 100, "black", 1000, 0);

        when(this.motorcycleService.getMotorcycles()).thenReturn(List.of(motorcycleResponse));

        final ResponseEntity<List<MotorcycleResponse>> response = this.motorcycleController.getMotorcycles();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
    }

    @Test
    void save() {
        MotorcycleEntity motorcycle = MotorcycleEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .engineDisplacement(0)
                .build();

        MotorcycleRequest request = new MotorcycleRequest(100, "black", 1000, 0);

        when(this.motorcycleService.addMotorcycle(any(MotorcycleRequest.class))).thenReturn(motorcycle);

        final MotorcycleEntity result = this.motorcycleController.addMotorcycle(request);

        assertEquals(result, motorcycle);
    }
}