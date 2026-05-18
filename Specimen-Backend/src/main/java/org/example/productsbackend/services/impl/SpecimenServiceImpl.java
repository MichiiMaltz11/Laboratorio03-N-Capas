package org.example.productsbackend.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.productsbackend.common.mappers.SpecimenMapper;
import org.example.productsbackend.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.productsbackend.domain.dto.request.specimen.UpdateSpecimenRequest;
import org.example.productsbackend.domain.dto.response.specimen.SpecimenResponse;
import org.example.productsbackend.domain.entities.Specimen;
import org.example.productsbackend.exceptions.ResourceNotFound;
import org.example.productsbackend.repositories.SpecimenRepository;
import org.example.productsbackend.services.SpecimenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(
                specimenRepository.save(
                        specimenMapper.toEntityCreate(request)
                )
        );
    }

    @Override
    public List<SpecimenResponse> getAllSpecimens() {
        List<Specimen> specimens = specimenRepository.findAll();
        if (specimens.isEmpty())
            throw new ResourceNotFound("No specimens are registered in Hyrule");

        return specimens.stream()
                .map(specimenMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Specimen not found"))
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        return specimenMapper.toDto(specimenRepository.save(specimenMapper.toEntityUpdate(request, id)));
    }

    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}

