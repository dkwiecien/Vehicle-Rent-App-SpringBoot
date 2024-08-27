package com.example.demo.rents.controllers;

import com.example.demo.rents.dtos.RentRequest;
import com.example.demo.rents.dtos.RentResponse;
import com.example.demo.rents.entities.RentEntity;
import com.example.demo.rents.services.RentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RentResponse>> getRents() {
        return ResponseEntity.ok(this.rentService.getRents());
    }

    @PostMapping("/save")
    public RentEntity save(@RequestBody RentRequest request) throws BadRequestException {
        return this.rentService.save(request);
    }
}
