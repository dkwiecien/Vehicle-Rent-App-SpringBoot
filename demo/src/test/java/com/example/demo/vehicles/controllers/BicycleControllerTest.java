package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.BicycleRequest;
import com.example.demo.vehicles.dtos.BicycleResponse;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.services.BicycleService;
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
class BicycleControllerTest {

    @Mock
    BicycleService bicycleService;
    @InjectMocks
    BicycleController bicycleController;

    @Test
    void getBicyclesTest() {
        BicycleResponse bicycleResponse = new BicycleResponse(UUID.randomUUID(), 100, "black", 1000, false);

        when(this.bicycleService.getBicycles()).thenReturn(List.of(bicycleResponse));

        final ResponseEntity<List<BicycleResponse>> response = this.bicycleController.getBicycles();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
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

        when(this.bicycleService.addBicycle(any(BicycleRequest.class))).thenReturn(bicycle);

        final BicycleEntity result = this.bicycleController.addBicycle(request);

        assertEquals(result, bicycle);
    }
}