package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.CarRequest;
import com.example.demo.vehicles.dtos.CarResponse;
import com.example.demo.vehicles.entities.CarEntity;
import com.example.demo.vehicles.repositories.CarsRepository;
import com.example.demo.vehicles.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarsRepository carsRepository;

    @Override
    public List<CarResponse> getCars() {
        return this.carsRepository.findAll()
                .stream().
                map(car -> new CarResponse(
                        car.getId(),
                        car.getWeight(),
                        car.getColor(),
                        car.getPrice(),
                        car.getNumberOfSeats())).toList();
    }

    @Override
    public CarEntity addCar(CarRequest request) {
        CarEntity car = CarEntity.builder()
                .price(request.price())
                .color(request.color())
                .weight(request.weight())
                .build();

        return this.carsRepository.save(car);
    }
}
