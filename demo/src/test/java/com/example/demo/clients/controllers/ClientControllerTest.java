package com.example.demo.clients.controllers;

import com.example.demo.addresses.dtos.AddressRequest;
import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientService clientService;
    @InjectMocks
    ClientController clientController;

    @Test
    void getClientsTest() {
        AddressResponse addressResponse = new AddressResponse(UUID.randomUUID(), "Warsaw", 12345, "Złota", 39);
        ClientResponse clientResponse = new ClientResponse("Dominik", "Kwiecień", addressResponse);

        when(this.clientService.getClients()).thenReturn(List.of(clientResponse));

        final ResponseEntity<List<ClientResponse>> response = this.clientController.getClients();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
    }

    @Test
    void addClientTest() {
        AddressEntity address = AddressEntity.builder()
                .city("Warsaw")
                .postCode(12345)
                .streetName("Złota")
                .streetNumber(39)
                .build();
        ClientEntity client = ClientEntity.builder()
                .firstName("Dominik")
                .lastName("Kwiecień")
                .address(address)
                .build();

        ClientRequest clientRequest = new ClientRequest(1234567890L, "Dominik", "Kwiecień", UUID.randomUUID());

        when(this.clientService.addClient(any(ClientRequest.class))).thenReturn(client);

        final ClientEntity result = this.clientController.addClient(clientRequest);

        assertEquals(result, client);
    }
}