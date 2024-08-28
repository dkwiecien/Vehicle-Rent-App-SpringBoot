package com.example.demo.clients.services.implementations;

import ch.qos.logback.core.net.server.Client;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    ClientServiceImpl clientService;

    @Test
    void getClientsTest() {
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

        when(this.clientRepository.findAll()).thenReturn(List.of(client));

        final List<ClientResponse> clients = this.clientService.getClients();

        assertFalse(clients.isEmpty());
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

        when(this.addressRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(address));
        when(this.clientRepository.save(any(ClientEntity.class))).thenReturn(client);

        final ClientEntity result = this.clientService.addClient(clientRequest);

        assertEquals(result, client);
    }
}