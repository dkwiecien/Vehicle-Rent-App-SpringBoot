package com.example.demo.addresses.controllers;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAddresses() {
        return ResponseEntity.ok(this.addressService.getAddresses());
    }

    @PostMapping
    public AddressEntity addAddress(@RequestBody AddressRequest request) {
        return this.addressService.addAddress(request);
    }
}
