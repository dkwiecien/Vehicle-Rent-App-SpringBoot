package com.example.demo.clients.dtos;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;

public record ClientRequest(
        int id,
        String firstName,
        String lastName,
        AddressRequest address
) {
}
