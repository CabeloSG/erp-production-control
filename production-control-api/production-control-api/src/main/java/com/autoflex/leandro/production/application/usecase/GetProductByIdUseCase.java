package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.*;
import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.repository.ProductRepository;
import com.autoflex.leandro.production.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;

    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO execute(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return toResponse(product);
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