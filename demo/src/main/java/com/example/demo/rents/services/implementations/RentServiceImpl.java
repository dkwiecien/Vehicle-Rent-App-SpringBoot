package com.example.demo.rents.services.implementations;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.repositories.ClientRepository;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.rents.dtos.RentRequest;
import com.example.demo.rents.dtos.RentResponse;
import com.example.demo.rents.entities.RentEntity;
import com.example.demo.rents.repositories.RentRepository;
import com.example.demo.rents.services.RentService;
import com.example.demo.vehicles.dtos.VehicleResponse;
import com.example.demo.vehicles.entities.VehicleEntity;
import com.example.demo.vehicles.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public List<RentResponse> getRents() {
        return this.rentRepository.findAll().stream().map(
                rentEntity -> new RentResponse(
                        rentEntity.getId(),
                        rentEntity.getPrice(),
                        rentEntity.isArchive(),
                        new ClientResponse(
                                rentEntity.getClient().getFirstName(),
                                rentEntity.getClient().getLastName(),
                                new AddressResponse(
                                        rentEntity.getClient().getAddress().getId(),
                                        rentEntity.getClient().getAddress().getCity(),
                                        rentEntity.getClient().getAddress().getPostCode(),
                                        rentEntity.getClient().getAddress().getStreetName(),
                                        rentEntity.getClient().getAddress().getStreetNumber()
                                )
                        ),
                        new VehicleResponse(
                                rentEntity.getVehicle().getWeight(),
                                rentEntity.getVehicle().getColor(),
                                rentEntity.getVehicle().getPrice()
                        )
                )
        ).toList();
    }

    @Override
    public RentEntity save(RentRequest request) throws BadRequestException {
        ClientEntity client = this.clientRepository.findById(request.clientId())
                .orElseThrow(() -> new NotFoundException("Client with Id: " + request.clientId() + " does not exist"));

        VehicleEntity vehicle = this.vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new NotFoundException("Vehicle with Id: " + request.vehicleId() + " does not exist"));

        List<RentEntity> currentRents = this.rentRepository.findByClientIdAndIsArchiveFalse(client.getId());
        if (currentRents.size() >= 2) throw new BadRequestException("Rent limit has been already reached");

        for (RentEntity rent : currentRents) {
            if (rent.getVehicle().getId() == vehicle.getId()) throw new BadRequestException("Vehicle with id + " +
                    vehicle.getId() + " is currently rented");
        }

        RentEntity newRent = RentEntity.builder()
                .price(request.price())
                .isArchive(request.isArchive())
                .client(client)
                .vehicle(vehicle)
                .build();

        return this.rentRepository.save(newRent);
    }
}
