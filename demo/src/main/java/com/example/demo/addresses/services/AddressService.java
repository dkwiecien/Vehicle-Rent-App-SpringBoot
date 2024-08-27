package com.example.demo.addresses.services;

import com.example.demo.addresses.dtos.AddressDto;
import com.example.demo.addresses.entities.AddressEntity;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddresses();
    AddressEntity save(AddressDto request);
}
