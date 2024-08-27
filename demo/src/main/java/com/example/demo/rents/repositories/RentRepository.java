package com.example.demo.rents.repositories;

import com.example.demo.rents.entities.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RentRepository extends JpaRepository<RentEntity, UUID> {
    @Query("""
            SELECT r
            FROM rents r
            JOIN r.client c
            WHERE c.id = :clientId AND r.isArchive = false
            """)
    List<RentEntity> findByClientIdAndIsArchiveFalse(Long clientId);
}
