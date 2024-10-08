package com.example.demo.clients.controllers;

import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getClients() {
        return ResponseEntity.ok(this.clientService.getClients());
    }

    @PostMapping
    public ClientEntity addClient(@RequestBody ClientRequest request) {
        return this.clientService.addClient(request);
    }
}
