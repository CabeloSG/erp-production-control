package com.autoflex.leandro.production.infrastructure.controller;

import com.autoflex.leandro.production.application.dto.*;
import com.autoflex.leandro.production.application.usecase.*;
import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products/{productId}/bill-of-materials")
public class ProductBillOfMaterialsController {

    private final AssociateRawMaterialToProductUseCase associateUseCase;
    private final ListProductBillOfMaterialsUseCase listUseCase;
    private final CalculateProductionCapacityUseCase capacityUseCase;
    private final  UpdateBillOfMaterialUseCase updateUseCase;
    private final DeleteBillOfMaterialUseCase deleteUseCase;

    public ProductBillOfMaterialsController(
            AssociateRawMaterialToProductUseCase associateUseCase,
            ListProductBillOfMaterialsUseCase listUseCase,
            CalculateProductionCapacityUseCase capacityUseCase,
            UpdateBillOfMaterialUseCase updateUseCase,
            DeleteBillOfMaterialUseCase deleteUseCase
    ) {
        this.associateUseCase = associateUseCase;
        this.listUseCase = listUseCase;
        this.capacityUseCase = capacityUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    // RF003
    @PostMapping
    public ResponseEntity<Void> associateRawMaterial(
            @PathVariable Long productId,
            @RequestBody AssociateRawMaterialRequestDTO request
    ) {

        associateUseCase.execute(
                productId,
                request.getRawMaterialId(),
                request.getQuantity()
        );

        return ResponseEntity.ok().build();
    }

    // RF004
    @GetMapping
    public ResponseEntity<List<BillOfMaterialResponseDTO>> listBillOfMaterials(
            @PathVariable Long productId
    ) {

        List<ProductRawMaterial> result =
                listUseCase.execute(productId);

        List<BillOfMaterialResponseDTO> response =
                result.stream()
                        .map(prm -> new BillOfMaterialResponseDTO(
                                prm.getId(),
                                prm.getRawMaterial().getId(),
                                prm.getRawMaterial().getCode(),
                                prm.getRawMaterial().getName(),
                                prm.getQuantityRequired()
                        ))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/production-capacity")
    public ResponseEntity<ProductionCapacityResponseDTO>
    calculateProductionCapacity(
            @PathVariable Long productId) {

        ProductionCapacityResponseDTO response =
                capacityUseCase.execute(productId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody UpdateBillOfMaterialRequestDTO dto
    ) {
        updateUseCase.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
    }

}