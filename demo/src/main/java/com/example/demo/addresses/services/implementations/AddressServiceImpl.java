package com.example.demo.addresses.services.implementations;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
import com.example.demo.addresses.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<AddressResponse> getAddresses() {
        return this.addressRepository.findAll().stream().map(
                addressEntity -> new AddressResponse(
                        addressEntity.getCity(),
                        addressEntity.getPostCode(),
                        addressEntity.getStreetName(),
                        addressEntity.getStreetNumber()
                )
        ).toList();
    }

    @Override
    public AddressEntity save(AddressResponse request) {
        AddressEntity newAddress = new AddressEntity(
                request.city(),
                request.postCode(),
                request.streetName(),
                request.streetNumber()
        );

        return this.addressRepository.save(newAddress);
    }
}
