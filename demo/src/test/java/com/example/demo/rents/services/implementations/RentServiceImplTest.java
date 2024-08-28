package com.example.demo.rents.services.implementations;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.repositories.ClientRepository;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.rents.dtos.RentRequest;
import com.example.demo.rents.dtos.RentResponse;
import com.example.demo.rents.entities.RentEntity;
import com.example.demo.rents.repositories.RentRepository;
import com.example.demo.vehicles.dtos.VehicleResponse;
import com.example.demo.vehicles.entities.BicycleEntity;
import com.example.demo.vehicles.repositories.BicycleRepository;
import com.example.demo.vehicles.repositories.VehicleRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentServiceImplTest {

    @Mock
    RentRepository rentRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    VehicleRepository vehicleRepository;
    @InjectMocks
    RentServiceImpl rentService;

    @Test
    void getRentsTest() {

        AddressEntity address = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();
        ClientEntity client = ClientEntity.builder()
                .firstName("Dominik")
                .lastName("Kwiecień")
                .address(address)
                .build();
        BicycleEntity bicycle = BicycleEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        RentEntity rent = RentEntity.builder()
                .price(1000)
                .isArchive(false)
                .client(client)
                .vehicle(bicycle)
                .build();

        when(this.rentRepository.findAll()).thenReturn(List.of(rent));

        final List<RentResponse> rents = this.rentService.getRents();

        assertFalse(rents.isEmpty());
    }

    @Test
    void addRentTest_success() throws BadRequestException {

        AddressEntity address = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();
        ClientEntity client = ClientEntity.builder()
                .firstName("Dominik")
                .lastName("Kwiecień")
                .address(address)
                .build();
        BicycleEntity bicycle = BicycleEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        RentEntity rent = RentEntity.builder()
                .price(1000)
                .isArchive(false)
                .client(client)
                .vehicle(bicycle)
                .build();

        RentRequest request = new RentRequest(UUID.randomUUID(), 1000, false, 1234567890L, UUID.randomUUID());

        when(this.clientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(client));
        when(this.vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(bicycle));
        when(this.rentRepository.save(any(RentEntity.class))).thenReturn(rent);

        final RentEntity result = this.rentService.addRent(request);

        assertEquals(result, rent);
    }

    @Test
    public void addRentTest_givenNullClientId_throwsNotFoundException() throws BadRequestException {
        Long clientId = null;

        RentRequest request = new RentRequest(UUID.randomUUID(), 1000, false, clientId, UUID.randomUUID());
        assertThrows(NotFoundException.class, () -> this.rentService.addRent(request), "Client with Id: " + clientId + " does not exist");
    }

    @Test
    public void addRentTest_givenNullVehicleId_throwsNotFoundException() throws BadRequestException {
        UUID vehicleId = null;

        RentRequest request = new RentRequest(UUID.randomUUID(), 1000, false, 1234567890L, vehicleId);
        assertThrows(NotFoundException.class, () -> this.rentService.addRent(request), "Vehicle with Id: " + vehicleId + " does not exist");
    }

    @Test
    public void addRentTest_rentLimitReached_throwsBadRequestException() {
        AddressEntity address1 = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();
        ClientEntity client1 = ClientEntity.builder()
                .id(1234567890L)
                .firstName("Dominik")
                .lastName("Kwiecień")
                .address(address1)
                .build();
        BicycleEntity bicycle1 = BicycleEntity.builder()
                .color("black")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        RentEntity rent1 = RentEntity.builder()
                .price(1000)
                .isArchive(false)
                .client(client1)
                .vehicle(bicycle1)
                .build();

        BicycleEntity bicycle2 = BicycleEntity.builder()
                .color("white")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        RentEntity rent2 = RentEntity.builder()
                .price(1000)
                .isArchive(false)
                .client(client1)
                .vehicle(bicycle2)
                .build();

        when(this.clientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(client1));
        lenient().when(this.vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(bicycle1));
        lenient().when(this.vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(bicycle2));

        when(this.rentRepository.findByClientIdAndIsArchiveFalse(any(Long.class))).thenReturn(List.of(rent1, rent2));

        RentRequest request = new RentRequest(UUID.randomUUID(), 1000, false, client1.getId(), UUID.randomUUID());

        assertThrows(BadRequestException.class, () -> this.rentService.addRent(request), "Rent limit has been already reached");
    }

    @Test
    public void addRentTest_vehicleAlreadyRented_throwsBadRequestException() {
        UUID vehicleId = UUID.randomUUID();

        AddressEntity address1 = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();
        ClientEntity client1 = ClientEntity.builder()
                .id(1234567890L)
                .firstName("Dominik")
                .lastName("Kwiecień")
                .address(address1)
                .build();
        BicycleEntity bicycle1 = BicycleEntity.builder()
                .id(vehicleId)
                .color("black")
                .price(1000)
                .weight(100)
                .helperWheels(false)
                .build();

        RentEntity rent1 = RentEntity.builder()
                .price(1000)
                .isArchive(false)
                .client(client1)
                .vehicle(bicycle1)
                .build();

        when(this.clientRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(client1));
        when(this.vehicleRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(bicycle1));
        when(this.rentRepository.findByClientIdAndIsArchiveFalse(any(Long.class))).thenReturn(List.of(rent1));

        RentRequest request = new RentRequest(UUID.randomUUID(), 1000, false, client1.getId(), vehicleId);

        assertThrows(BadRequestException.class, () -> this.rentService.addRent(request), "Vehicle with id + " +
                vehicleId + " is currently rented");
    }
}