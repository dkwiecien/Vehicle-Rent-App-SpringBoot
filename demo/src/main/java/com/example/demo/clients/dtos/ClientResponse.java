package com.example.demo.clients.dtos;

import com.example.demo.addresses.dtos.AddressResponse;

public record ClientResponse(
        String firstName,
        String lastName,
        AddressResponse address
) {
}
