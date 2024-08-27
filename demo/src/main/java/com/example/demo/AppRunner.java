package com.example.demo;

import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.repositories.ClientRepository;
import com.example.demo.rents.entities.RentEntity;
import com.example.demo.rents.repositories.RentRepository;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.entities.CarEntity;
import com.example.demo.vehicles.entities.MotorcycleEntity;
import com.example.demo.vehicles.entities.VehicleEntity;
import com.example.demo.vehicles.repositories.BicycleRepository;
import com.example.demo.vehicles.repositories.CarsRepository;
import com.example.demo.vehicles.repositories.MotorcycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final CarsRepository carsRepository;
    private final BicycleRepository bicycleRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final RentRepository rentRepository;

    @Override
    public void run(String... args) throws Exception {
        CarEntity car = new CarEntity(500, 2500, "black", false, 5);
        car = this.carsRepository.save(car);

        BicycleEntity bicycle = new BicycleEntity(20, "silver", 100, false, false);
        bicycle = this.bicycleRepository.save(bicycle);

        MotorcycleEntity motorcycle = new MotorcycleEntity(80, "blue", 300.5, false, 0);
        motorcycle = this.motorcycleRepository.save(motorcycle);

        AddressEntity address = AddressEntity.builder().city("Warsaw").postCode(12345).streetName("Złota").streetNumber(39).build();
        address = this.addressRepository.save(address);

        ClientEntity client = ClientEntity.builder().id(12312303495L).firstName("Dominik").lastName("Kwiecień").address(address).build();
        client = this.clientRepository.save(client);

        RentEntity rent1 = RentEntity.builder().price(car.getPrice()).isArchive(false).client(client).vehicle(car).build();
        RentEntity rent2 = RentEntity.builder().price(car.getPrice()).isArchive(false).client(client).vehicle(bicycle).build();

        this.rentRepository.save(rent1);
        this.rentRepository.save(rent2);
    }
}
