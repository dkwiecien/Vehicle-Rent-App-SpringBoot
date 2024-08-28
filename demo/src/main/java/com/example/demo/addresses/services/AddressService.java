package com.example.demo.addresses.services;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAddresses();
    AddressEntity addAddress(AddressRequest request);
}
