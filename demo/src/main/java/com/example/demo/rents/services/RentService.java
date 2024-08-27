package com.example.demo.rents.services;

import com.example.demo.rents.dtos.RentRequest;
import com.example.demo.rents.dtos.RentResponse;
import com.example.demo.rents.entities.RentEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface RentService {
    List<RentResponse> getRents();
    RentEntity save(RentRequest request) throws BadRequestException;
}
