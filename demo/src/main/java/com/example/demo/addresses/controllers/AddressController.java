package com.example.demo.addresses.controllers;

import com.example.demo.addresses.dtos.AddressDto;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<AddressDto>> getAddresses() {
        return ResponseEntity.ok(this.addressService.getAddresses());
    }

    @PostMapping("/save")
    public AddressEntity save(@RequestBody AddressDto request) {
        return this.addressService.save(request);
    }
}
