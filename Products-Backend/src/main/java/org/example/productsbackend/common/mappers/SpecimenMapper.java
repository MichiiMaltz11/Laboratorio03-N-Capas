package org.example.productsbackend.common.mappers;

import org.example.productsbackend.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.productsbackend.domain.dto.request.specimen.UpdateSpecimenRequest;
import org.example.productsbackend.domain.dto.response.specimen.SpecimenResponse;
import org.example.productsbackend.domain.entities.Specimen;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SpecimenMapper {
    public Specimen toEntityCreate(CreateSpecimenRequest request) {
        return Specimen.builder()
                .name(request.getName())
                .region(request.getRegion())
                .dangerLevel(request.getDangerLevel())
                .isFriendly(request.getIsFriendly())
                .build();
    }

    public Specimen toEntityUpdate(UpdateSpecimenRequest updateSpecimenRequest, UUID id) {
        return Specimen.builder()
                .id(id)
                .name(updateSpecimenRequest.getName())
                .region(updateSpecimenRequest.getRegion())
                .dangerLevel(updateSpecimenRequest.getDangerLevel())
                .isFriendly(updateSpecimenRequest.getIsFriendly())
                .build();
    }

    public SpecimenResponse toDto(Specimen specimen) {
        return SpecimenResponse.builder()
                .id(specimen.getId())
                .name(specimen.getName())
                .region(specimen.getRegion())
                .dangerLevel(specimen.getDangerLevel())
                .isFriendly(specimen.getIsFriendly())
                .build();
    }

    public List<SpecimenResponse> toDtoList(List<Specimen> specimens) {
        return specimens.stream()
                .map(this::toDto)
                .toList();
    }
}

