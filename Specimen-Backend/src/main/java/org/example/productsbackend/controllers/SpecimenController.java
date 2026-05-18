package org.example.productsbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productsbackend.domain.dto.request.specimen.CreateSpecimenRequest;
import org.example.productsbackend.domain.dto.request.specimen.UpdateSpecimenRequest;
import org.example.productsbackend.domain.dto.response.GeneralResponse;
import org.example.productsbackend.services.impl.SpecimenServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/specimen")
@RequiredArgsConstructor
public class SpecimenController {
    private final SpecimenServiceImpl specimenService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createSpecimen(@RequestBody @Valid CreateSpecimenRequest specimen) {
        return buildResponse(
                "Specimen created successfully",
                HttpStatus.CREATED,
                specimenService.createSpecimen(specimen)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<GeneralResponse> getAllSpecimens(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder
    ) {
        return buildResponse(
                "Specimens found successfully",
                HttpStatus.OK,
                specimenService.getAllSpecimens(page, size, sortBy, sortOrder)
        );
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<GeneralResponse> getSpecimenById(@PathVariable UUID id) {
        return buildResponse(
                "Specimen found successfully",
                HttpStatus.OK,
                specimenService.getSpecimenById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponse> updateSpecimen(
            @PathVariable UUID id,
            @RequestBody UpdateSpecimenRequest specimen
    ) {
        return buildResponse(
                "Specimen updated successfully",
                HttpStatus.OK,
                specimenService.updateSpecimen(id, specimen));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteSpecimen(@PathVariable UUID id) {
        return buildResponse(
                "Specimen deleted successfully",
                HttpStatus.OK,
                specimenService.deleteSpecimen(id)
        );
    }

    private ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }
}


