package com.example.demo.clients.services;

import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    List<ClientResponse> getClients();
    ClientEntity save(ClientRequest request);
}
