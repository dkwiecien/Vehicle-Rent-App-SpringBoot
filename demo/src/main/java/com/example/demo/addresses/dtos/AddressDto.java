package com.example.demo.addresses.dtos;

public record AddressDto(
        String city,
        int postCode,
        String streetName,
        int streetNumber
) {
}
