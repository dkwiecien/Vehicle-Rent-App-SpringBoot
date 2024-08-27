package com.example.demo.clients.entities;

import com.example.demo.addresses.entities.AddressEntity;
import com.example.demo.rents.entities.RentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clients")
public class ClientEntity {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;
    @OneToMany(mappedBy = "client")
    private List<RentEntity> rents;

    public ClientEntity(Long id, String firstName, String lastName, AddressEntity address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
