package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.*;
import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.repository.ProductRepository;
import com.autoflex.leandro.production.infrastructure.exception.DuplicateResourceException;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO execute(Long id, ProductRequestDTO dto) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado com ID: " + id)
                );

        // Valida duplicidade apenas se o código mudou
        if (!existing.getCode().equals(dto.getCode()) &&
                productRepository.existsByCode(dto.getCode())) {

            throw new DuplicateResourceException(
                    "Já existe produto com código: " + dto.getCode()
            );
        }

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setValue(dto.getValue());

        Product saved = productRepository.save(existing);

        return toResponse(saved);
    }

    private ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getValue()
        );
    }
}