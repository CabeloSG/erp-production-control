package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.infrastructure.persistence.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductJpaEntity, Long> {
    boolean existsByCode(String code);
}