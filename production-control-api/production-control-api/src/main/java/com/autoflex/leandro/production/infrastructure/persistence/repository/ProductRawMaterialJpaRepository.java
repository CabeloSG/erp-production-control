package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import com.autoflex.leandro.production.infrastructure.persistence.entity.ProductRawMaterialJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRawMaterialJpaRepository extends JpaRepository<ProductRawMaterialJpaEntity, Long> {

    List<ProductRawMaterialJpaEntity> findByProduct_Id(Long productId);

    List<ProductRawMaterialJpaEntity> findByRawMaterial_Id(Long rawMaterialId);

    boolean existsByProduct_IdAndRawMaterial_Id(Long productId, Long rawMaterialId);

    @Modifying
    @Query("""
        update ProductRawMaterialJpaEntity prm
           set prm.quantityRequired = :quantity
         where prm.id = :id
    """)
    void updateQuantity(
            @Param("id") Long id,
            @Param("quantity") Double quantity
    );
}