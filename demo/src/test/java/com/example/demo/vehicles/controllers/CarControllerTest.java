package com.example.demo.vehicles.controllers;

import com.example.demo.vehicles.dtos.CarRequest;
import com.example.demo.vehicles.dtos.CarResponse;
import com.example.demo.vehicles.entities.CarEntity;
import com.example.demo.vehicles.services.CarService;
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
class CarControllerTest {

    @Mock
    CarService carService;
    @InjectMocks
    CarController carController;

    @Test
    void getCarsTest() {
        CarResponse carResponse = new CarResponse(UUID.randomUUID(), 100, "black", 1000, 5);

        when(this.carService.getCars()).thenReturn(List.of(carResponse));

        final ResponseEntity<List<CarResponse>> response = this.carController.getCars();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
    }

    @Test
    void addCarTest() {
        CarEntity car = CarEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .numberOfSeats(5)
                .build();

        CarRequest request = new CarRequest(100, "black", 1000, 5);

        when(this.carService.addCar(any(CarRequest.class))).thenReturn(car);

        final CarEntity result = this.carController.addCar(request);

        assertEquals(result, car);
    }
}