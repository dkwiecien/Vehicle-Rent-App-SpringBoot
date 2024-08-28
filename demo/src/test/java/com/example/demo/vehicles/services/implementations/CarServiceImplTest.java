package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.BicycleRequest;
import com.example.demo.vehicles.dtos.BicycleResponse;
import com.example.demo.vehicles.dtos.CarRequest;
import com.example.demo.vehicles.dtos.CarResponse;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.entities.CarEntity;
import com.example.demo.vehicles.repositories.CarsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    CarsRepository carsRepository;
    @InjectMocks
    CarServiceImpl carService;

    @Test
    void getCarsTest() {
        CarEntity car = CarEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .numberOfSeats(5)
                .build();

        when(this.carsRepository.findAll()).thenReturn(List.of(car));

        final List<CarResponse> cars = this.carService.getCars();

        assertFalse(cars.isEmpty());
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

        when(this.carsRepository.save(any(CarEntity.class))).thenReturn(car);

        final CarEntity result = this.carService.addCar(request);

        assertEquals(result, car);
    }
}