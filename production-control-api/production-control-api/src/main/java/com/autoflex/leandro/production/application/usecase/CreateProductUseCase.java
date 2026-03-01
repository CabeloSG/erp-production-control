package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.*;
import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.repository.ProductRepository;
import com.autoflex.leandro.production.infrastructure.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO execute(ProductRequestDTO dto) {

        if (productRepository.existsByCode(dto.getCode())) {
            throw new DuplicateResourceException(
                    "Já existe produto com código: " + dto.getCode()
            );
        }

        Product product = new Product(
                null,
                dto.getCode(),
                dto.getName(),
                dto.getValue()
        );

        Product saved = productRepository.save(product);

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