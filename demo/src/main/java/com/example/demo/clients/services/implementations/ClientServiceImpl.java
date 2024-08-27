package com.example.demo.clients.services.implementations;

import com.example.demo.addresses.dtos.AddressResponse;
import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.clients.dtos.ClientRequest;
import com.example.demo.clients.dtos.ClientResponse;
import com.example.demo.clients.entities.ClientEntity;
import com.example.demo.clients.repositories.ClientRepository;
import com.example.demo.clients.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponse> getClients() {
        return this.clientRepository.findAll().stream().map(
                clientEntity -> new ClientResponse(
                        clientEntity.getFirstName(),
                        clientEntity.getLastName(),
                        new AddressResponse(
                                clientEntity.getAddress().getCity(),
                                clientEntity.getAddress().getPostCode(),
                                clientEntity.getAddress().getStreetName(),
                                clientEntity.getAddress().getStreetNumber()
                        )
                )
        ).toList();
    }

    @Override
    public ClientEntity save(ClientRequest request) {
        ClientEntity newClient = new ClientEntity(
                request.id(),
                request.firstName(),
                request.lastName(),
                new AddressEntity(
                        request.address().id(),
                        request.address().city(),
                        request.address().postCode(),
                        request.address().streetName(),
                        request.address().streetNumber()
                )
        );

        return this.clientRepository.save(newClient);
    }
}
