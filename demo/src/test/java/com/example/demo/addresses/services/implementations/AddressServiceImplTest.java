package com.example.demo.addresses.services.implementations;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
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
class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    AddressServiceImpl addressService;

    @Test
    void getAddressesTest() {
        AddressEntity address = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();

        when(this.addressRepository.findAll()).thenReturn(List.of(address));

        final List<AddressResponse> addresses = this.addressService.getAddresses();

        assertFalse(addresses.isEmpty());
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


        when(this.addressRepository.save(any(AddressEntity.class))).thenReturn(address);

        final AddressEntity result = this.addressService.addAddress(request);

        assertEquals(result, address);
    }
}