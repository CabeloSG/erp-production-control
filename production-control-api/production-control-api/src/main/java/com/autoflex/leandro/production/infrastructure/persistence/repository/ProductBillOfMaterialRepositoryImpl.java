package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import com.autoflex.leandro.production.domain.repository.ProductBillOfMaterialRepository;
import com.autoflex.leandro.production.infrastructure.persistence.entity.ProductRawMaterialJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductBillOfMaterialRepositoryImpl
        implements ProductBillOfMaterialRepository {

    private final ProductRawMaterialJpaRepository jpa;

    public ProductBillOfMaterialRepositoryImpl(
            ProductRawMaterialJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<ProductRawMaterial> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public ProductRawMaterial save(ProductRawMaterial bom) {
        var saved = jpa.save(toJpa(bom));
        return toDomain(saved);
    }

    @Override
    public void delete(ProductRawMaterial bom) {
        jpa.delete(toJpa(bom));
    }

    @Override
    public void updateQuantity(Long id, Double quantity) {
        jpa.updateQuantity(id, quantity);
    }

    // mapper básico
    private ProductRawMaterial toDomain(ProductRawMaterialJpaEntity e) {
        return new ProductRawMaterial(
                e.getId(),
                null,
                null,
                e.getQuantityRequired()
        );
    }

    private ProductRawMaterialJpaEntity toJpa(ProductRawMaterial d) {
        ProductRawMaterialJpaEntity e =
                new ProductRawMaterialJpaEntity();
        e.setId(d.getId());
        e.setQuantityRequired(d.getQuantityRequired());
        return e;
    }
}