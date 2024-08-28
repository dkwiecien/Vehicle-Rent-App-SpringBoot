package com.example.demo.clients.dtos;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;

import java.util.UUID;

public record ClientRequest(
        Long id,
        String firstName,
        String lastName,
        UUID addressId
) {
}
