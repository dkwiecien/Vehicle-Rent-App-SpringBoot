package com.example.demo.addresses.services;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAddresses();
    AddressEntity save(AddressResponse request);
}
