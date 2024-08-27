package com.example.demo.addresses.dtos;

import java.util.UUID;

public record AddressRequest(
        UUID id,
        String city,
        int postCode,
        String streetName,
        int streetNumber
) {
}
