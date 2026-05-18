package org.example.productsbackend.repositories;

import org.example.productsbackend.domain.entities.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, UUID> {
    Specimen findSpecimenById(UUID id);
}

