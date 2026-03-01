package com.autoflex.leandro.production.domain.repository;

import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;

import java.util.List;
import java.util.Optional;

public interface ProductRawMaterialRepository {

    ProductRawMaterial save(ProductRawMaterial association);

    Optional<ProductRawMaterial> findById(Long id);

    List<ProductRawMaterial> findAll();

    void deleteById(Long id);

    List<ProductRawMaterial> findByProductId(Long productId);

    List<ProductRawMaterial> findByRawMaterialId(Long rawMaterialId);

    boolean existsByProductIdAndRawMaterialId(Long productId, Long rawMaterialId);


}