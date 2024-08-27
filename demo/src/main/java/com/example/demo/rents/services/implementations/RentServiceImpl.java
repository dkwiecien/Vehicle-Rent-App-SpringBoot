package com.example.demo.rents.services.implementations;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.rents.dtos.RentRequest;
import com.example.demo.rents.dtos.RentResponse;
import com.example.demo.rents.entities.RentEntity;
import com.example.demo.rents.repositories.RentRepository;
import com.example.demo.rents.services.RentService;
import com.example.demo.vehicles.dtos.VehicleResponse;
import com.example.demo.vehicles.entities.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

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
                                        rentEntity.getClient().getAddress().getCity(),
                                        rentEntity.getClient().getAddress().getPostCode(),
                                        rentEntity.getClient().getAddress().getStreetName(),
                                        rentEntity.getClient().getAddress().getStreetNumber()
                                )
                        ),
                        new VehicleResponse(
                                rentEntity.getVehicle().getWeight(),
                                rentEntity.getVehicle().getColor(),
                                rentEntity.getVehicle().getPrice(),
                                rentEntity.getVehicle().isRented()
                        )
                )
        ).toList();
    }

    @Override
    public RentEntity save(RentRequest request) throws BadRequestException {
        ClientEntity client = new ClientEntity(
                request.client().id(),
                request.client().firstName(),
                request.client().lastName(),
                new AddressEntity(
                        request.client().address().id(),
                        request.client().address().city(),
                        request.client().address().postCode(),
                        request.client().address().streetName(),
                        request.client().address().streetNumber()
                )
        );

        VehicleEntity vehicle = new VehicleEntity(
                request.vehicle().id(),
                request.vehicle().weight(),
                request.vehicle().color(),
                request.vehicle().price(),
                request.vehicle().isRented()
        );

        List<RentEntity> currentRents = this.rentRepository.findByClientIdAndIsArchiveFalse(client.getId());
        if (currentRents.size() >= 2) throw new BadRequestException("Rent limit has been already reached");

        for (RentEntity rent : currentRents) {
            if (rent.getVehicle().getId() == vehicle.getId()) throw new BadRequestException("Vehicle with id + " +
                    vehicle.getId() + " is currently rented");
        }

        RentEntity newRent = new RentEntity(
                request.price(),
                request.isArchive(),
                client,
                vehicle
        );

        return this.rentRepository.save(newRent);
    }
}
