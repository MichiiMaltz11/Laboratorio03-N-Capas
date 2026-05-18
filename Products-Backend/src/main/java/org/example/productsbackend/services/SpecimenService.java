package org.example.productsbackend.services;

import jakarta.validation.Valid;
import org.example.productsbackend.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.productsbackend.domain.dto.request.specimen.UpdateSpecimenRequest;
import org.example.productsbackend.domain.dto.response.specimen.SpecimenResponse;

import java.util.List;
import java.util.UUID;

public interface SpecimenService {
    SpecimenResponse createSpecimen(CreateSpecimenRequest specimen);
    List<SpecimenResponse> getAllSpecimens();
    SpecimenResponse getSpecimenById(UUID id);
    SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest specimen);
    SpecimenResponse deleteSpecimen(UUID id);
}

