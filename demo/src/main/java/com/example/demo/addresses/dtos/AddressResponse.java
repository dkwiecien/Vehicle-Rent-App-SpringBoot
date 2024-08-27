package com.example.demo.addresses.dtos;

import java.util.UUID;

public record AddressResponse(
        String city,
        int postCode,
        String streetName,
        int streetNumber
) {
}
