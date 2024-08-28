package com.example.demo.addresses.controllers;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
import com.example.demo.addresses.services.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    AddressService addressService;
    @InjectMocks
    AddressController addressController;

    @Test
    void getAddressesTest() {
        AddressResponse addressResponse = new AddressResponse(UUID.randomUUID(), "Warsaw", 12345, "Złota", 39);

        when(this.addressService.getAddresses()).thenReturn(List.of(addressResponse));

        final ResponseEntity<List<AddressResponse>> response = this.addressController.getAddresses();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), List.of(addressResponse));
    }

    @Test
    void addAddressTest() {
        AddressEntity address = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();

        AddressRequest request = new AddressRequest("Warsaw", 12345, "Złota", 39);

        when(this.addressService.addAddress(any(AddressRequest.class))).thenReturn(address);

        final AddressEntity result = this.addressController.addAddress(request);

        assertEquals(result, address);

    }
}