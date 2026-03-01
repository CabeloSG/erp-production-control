package com.autoflex.leandro.production.application.mapper;

import com.autoflex.leandro.production.application.dto.ProductRequestDTO;
import com.autoflex.leandro.production.application.dto.ProductResponseDTO;
import com.autoflex.leandro.production.domain.entity.Product;
import com.autoflex.leandro.production.infrastructure.persistence.entity.ProductJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // DTO → Domain
    public static Product toDomain(ProductRequestDTO dto) {
        return new Product(null, dto.getCode(), dto.getName(), dto.getValue());
    }

    // Domain → DTO
    public static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getValue()
        );
    }

    // Domain → JPA Entity
    public static ProductJpaEntity toJpa(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setCode(product.getCode());
        entity.setName(product.getName());
        entity.setValue(product.getValue());
        return entity;
    }

    // JPA Entity → Domain
    public static Product toDomain(ProductJpaEntity entity) {
        return new Product(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getValue()
        );
    }
}