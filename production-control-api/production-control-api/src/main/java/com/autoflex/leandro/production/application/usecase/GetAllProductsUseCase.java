package com.autoflex.leandro.production.application.usecase;

import com.autoflex.leandro.production.application.dto.ProductResponseDTO;
import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> execute() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
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