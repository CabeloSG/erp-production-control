package com.autoflex.leandro.production.infrastructure.controller;

import com.autoflex.leandro.production.application.dto.RawMaterialRequestDTO;
import com.autoflex.leandro.production.application.dto.RawMaterialResponseDTO;
import com.autoflex.leandro.production.application.mapper.RawMaterialMapper;
import com.autoflex.leandro.production.application.usecase.*;
import com.autoflex.leandro.production.domain.entity.RawMaterial;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw-materials")
public class RawMaterialController {

    private final CreateRawMaterialUseCase createUseCase;
    private final UpdateRawMaterialUseCase updateUseCase;
    private final GetRawMaterialByIdUseCase getByIdUseCase;
    private final GetAllRawMaterialsUseCase getAllUseCase;
    private final DeleteRawMaterialUseCase deleteUseCase;

    public RawMaterialController(CreateRawMaterialUseCase createUseCase,
                                 UpdateRawMaterialUseCase updateUseCase,
                                 GetRawMaterialByIdUseCase getByIdUseCase,
                                 GetAllRawMaterialsUseCase getAllUseCase,
                                 DeleteRawMaterialUseCase deleteUseCase) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.getAllUseCase = getAllUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<RawMaterialResponseDTO> create(@Valid @RequestBody RawMaterialRequestDTO dto) {
        RawMaterial rawMaterial = RawMaterialMapper.toDomain(dto);
        RawMaterial saved = createUseCase.execute(rawMaterial);
        return ResponseEntity.status(201).body(RawMaterialMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterialResponseDTO> getById(@PathVariable Long id) {
        RawMaterial rawMaterial = getByIdUseCase.execute(id);
        return ResponseEntity.ok(RawMaterialMapper.toResponse(rawMaterial));
    }

    @GetMapping
    public ResponseEntity<List<RawMaterialResponseDTO>> getAll() {
        List<RawMaterialResponseDTO> response = getAllUseCase.execute()
                .stream()
                .map(RawMaterialMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterialResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody RawMaterialRequestDTO dto) {

        // DEBUG AQUI
        System.out.println("DTO CODE RECEBIDO: " + dto.getCode());
        System.out.println("DTO NAME RECEBIDO: " + dto.getName());
        System.out.println("DTO QTD RECEBIDA: " + dto.getQuantityInStock());

        RawMaterial rawMaterial = new RawMaterial(
                id,
                dto.getCode(),
                dto.getName(),
                dto.getQuantityInStock()
        );

        RawMaterial updated = updateUseCase.execute(id, rawMaterial);

        return ResponseEntity.ok(RawMaterialMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}