package com.example.demo.vehicles.services.implementations;

import com.example.demo.vehicles.dtos.CarDto;
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
    public List<CarDto> getAll() {
        return this.carsRepository.findAll()
                .stream().
                map(car -> new CarDto(car.getWeight(),
                        car.getColor(),
                        car.getPrice(),
                        car.isRented(),
                        car.getNumberOfSeats())).toList();
    }

    @Override
    public CarEntity save(CarDto request) {
        CarEntity car = new CarEntity(
                request.weight(),
                request.price(),
                request.color(),
                request.isRented(),
                request.numberOfSeats()
        );
        return this.carsRepository.save(car);
    }
}
