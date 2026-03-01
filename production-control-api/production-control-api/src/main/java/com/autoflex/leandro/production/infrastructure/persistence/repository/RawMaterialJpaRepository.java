package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.infrastructure.persistence.entity.RawMaterialJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialJpaRepository extends JpaRepository<RawMaterialJpaEntity, Long> {
    boolean existsByCode(String code);
}