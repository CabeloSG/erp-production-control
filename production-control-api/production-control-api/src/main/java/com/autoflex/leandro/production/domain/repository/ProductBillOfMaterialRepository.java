package com.autoflex.leandro.production.domain.repository;

import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductBillOfMaterialRepository {

    Optional<ProductRawMaterial> findById(Long id);

    ProductRawMaterial save(ProductRawMaterial entity);

    void delete(ProductRawMaterial entity);

    void updateQuantity(Long id, Double quantity);

}
