package com.example.demo.rents.controllers;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.rents.dtos.RentRequest;
import com.example.demo.rents.dtos.RentResponse;
import com.example.demo.rents.entities.RentEntity;
import com.example.demo.rents.services.RentService;
import com.example.demo.vehicles.dtos.BicycleResponse;
import com.example.demo.vehicles.dtos.VehicleResponse;
import com.example.demo.vehicles.entities.BicycleEntity;
import org.apache.coyote.BadRequestException;
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
class RentControllerTest {

    @Mock
    RentService rentService;
    @InjectMocks
    RentController rentController;

    @Test
    void getRentsTest() {
        AddressResponse addressResponse = new AddressResponse(UUID.randomUUID(), "Warsaw", 12345, "Złota", 39);
        ClientResponse clientResponse = new ClientResponse("Dominik", "Kwiecień", addressResponse);
        VehicleResponse vehicleResponse = new VehicleResponse(UUID.randomUUID(), 100, "black", 1000);
        RentResponse rentResponse = new RentResponse(UUID.randomUUID(), vehicleResponse.price(), false, clientResponse, vehicleResponse);

        when(this.rentService.getRents()).thenReturn(List.of(rentResponse));

        final ResponseEntity<List<RentResponse>> response = this.rentController.getRents();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
    }

    @Test
    void addRentTest() throws BadRequestException {
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

        when(this.rentService.addRent(any(RentRequest.class))).thenReturn(rent);

        final RentEntity result = this.rentController.addRent(request);

        assertEquals(result, rent);
    }
}