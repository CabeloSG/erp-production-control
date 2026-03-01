package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.*;
import com.autoflex.leandro.production.domain.entity.*;
import com.autoflex.leandro.production.domain.repository.*;
import com.autoflex.leandro.production.domain.service.ProductionCapacityCalculator;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateProductionCapacityUseCase {

    private final ProductRepository productRepository;
    private final ProductRawMaterialRepository repository;
    private final ProductionCapacityCalculator calculator;

    public CalculateProductionCapacityUseCase(
            ProductRepository productRepository,
            ProductRawMaterialRepository repository,
            ProductionCapacityCalculator calculator) {

        this.productRepository = productRepository;
        this.repository = repository;
        this.calculator = calculator;
    }

    public ProductionCapacityResponseDTO execute(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado"));

        List<ProductRawMaterial> bom =
                repository.findByProductId(productId);

        ProductionCapacityResult result =
                calculator.calculate(product, bom);

        RawMaterial limiting = result.getLimitingRawMaterial();

        RawMaterialLimitDTO limitDTO = null;

        if (limiting != null) {
            limitDTO = new RawMaterialLimitDTO(
                    limiting.getId(),
                    limiting.getCode(),
                    limiting.getName()
            );
        }

        // evita Integer.MAX_VALUE visual
        int safeCapacity =
                result.getCapacity() == Integer.MAX_VALUE
                        ? 0
                        : result.getCapacity();

        return new ProductionCapacityResponseDTO(
                product.getId(),
                product.getCode(),
                product.getName(),
                safeCapacity,
                limitDTO
        );
    }
}