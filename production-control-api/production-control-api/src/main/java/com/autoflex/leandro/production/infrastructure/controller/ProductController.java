package com.autoflex.leandro.production.infrastructure.controller;

import com.autoflex.leandro.production.application.dto.ProductRequestDTO;
import com.autoflex.leandro.production.application.dto.ProductResponseDTO;
import com.autoflex.leandro.production.application.usecase.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createUseCase;
    private final UpdateProductUseCase updateUseCase;
    private final GetProductByIdUseCase getByIdUseCase;
    private final GetAllProductsUseCase getAllUseCase;
    private final DeleteProductUseCase deleteUseCase;

    public ProductController(
            CreateProductUseCase createUseCase,
            UpdateProductUseCase updateUseCase,
            GetProductByIdUseCase getByIdUseCase,
            GetAllProductsUseCase getAllUseCase,
            DeleteProductUseCase deleteUseCase
    ) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.getAllUseCase = getAllUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @Valid @RequestBody ProductRequestDTO dto
    ) {
        ProductResponseDTO response = createUseCase.execute(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        ProductResponseDTO response = getByIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(getAllUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto
    ) {
        ProductResponseDTO response = updateUseCase.execute(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}