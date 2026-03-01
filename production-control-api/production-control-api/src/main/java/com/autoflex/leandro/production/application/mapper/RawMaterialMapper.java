package com.autoflex.leandro.production.application.mapper;

import com.autoflex.leandro.production.application.dto.RawMaterialRequestDTO;
import com.autoflex.leandro.production.application.dto.RawMaterialResponseDTO;
import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.infrastructure.persistence.entity.RawMaterialJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RawMaterialMapper {

    public static RawMaterial toDomain(RawMaterialRequestDTO dto) {
        return new RawMaterial(null, dto.getCode(), dto.getName(), dto.getQuantityInStock());
    }

    public static RawMaterialResponseDTO toResponse(RawMaterial rawMaterial) {
        return new RawMaterialResponseDTO(
                rawMaterial.getId(),
                rawMaterial.getCode(),
                rawMaterial.getName(),
                rawMaterial.getQuantityInStock()
        );
    }

    public static RawMaterialJpaEntity toJpa(RawMaterial rawMaterial) {
        RawMaterialJpaEntity entity = new RawMaterialJpaEntity();
        entity.setId(rawMaterial.getId());
        entity.setCode(rawMaterial.getCode());
        entity.setName(rawMaterial.getName());
        entity.setQuantityInStock(rawMaterial.getQuantityInStock());
        return entity;
    }

    public static RawMaterial toDomain(RawMaterialJpaEntity entity) {
        return new RawMaterial(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getQuantityInStock()
        );
    }
}