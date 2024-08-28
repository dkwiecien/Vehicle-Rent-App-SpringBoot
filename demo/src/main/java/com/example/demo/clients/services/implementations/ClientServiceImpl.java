package com.example.demo.clients.services.implementations;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.addresses.repositories.AddressRepository;
import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.repositories.ClientRepository;
import com.example.demo.clients.services.ClientService;
import com.example.demo.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<ClientResponse> getClients() {
        return this.clientRepository.findAll().stream().map(
                clientEntity -> new ClientResponse(
                        clientEntity.getFirstName(),
                        clientEntity.getLastName(),
                        new AddressResponse(
                                clientEntity.getAddress().getId(),
                                clientEntity.getAddress().getCity(),
                                clientEntity.getAddress().getPostCode(),
                                clientEntity.getAddress().getStreetName(),
                                clientEntity.getAddress().getStreetNumber()
                        )
                )
        ).toList();
    }

    @Override
    public ClientEntity addClient(ClientRequest request) throws NotFoundException {
        AddressEntity address = this.addressRepository.findById(request.addressId())
                .orElseThrow(() -> new NotFoundException("Address with Id: " + request.addressId() + " does not exist"));

        ClientEntity newClient = ClientEntity.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .address(address)
                .build();

        return this.clientRepository.save(newClient);
    }
}
