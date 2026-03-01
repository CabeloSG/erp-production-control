package com.autoflex.leandro.production.domain.repository;

import com.autoflex.leandro.production.domain.entity.RawMaterial;

import java.util.List;
import java.util.Optional;

public interface RawMaterialRepository {

    RawMaterial save(RawMaterial rawMaterial);

    Optional<RawMaterial> findById(Long id);

    List<RawMaterial> findAll();

    void deleteById(Long id);

    boolean existsByCode(String code);
}