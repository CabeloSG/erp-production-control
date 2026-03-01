package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.application.usecase.UpdateRawMaterialUseCase;
import com.autoflex.leandro.production.domain.entity.RawMaterial;
import com.autoflex.leandro.production.domain.repository.RawMaterialRepository;
import com.autoflex.leandro.production.infrastructure.persistence.entity.RawMaterialJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RawMaterialRepositoryImpl implements RawMaterialRepository {

    private static final Logger log = LoggerFactory.getLogger(RawMaterialRepositoryImpl.class);
    private final RawMaterialJpaRepository rawMaterialJpaRepository;

    public RawMaterialRepositoryImpl(RawMaterialJpaRepository rawMaterialJpaRepository) {
        this.rawMaterialJpaRepository = rawMaterialJpaRepository;
    }

    @Override
    public RawMaterial save(RawMaterial rawMaterial) {

        RawMaterialJpaEntity entity;

        if (rawMaterial.getId() != null) {

            entity = rawMaterialJpaRepository.findById(rawMaterial.getId())
                    .orElse(new RawMaterialJpaEntity());

        } else {
            entity = new RawMaterialJpaEntity();
        }

        log.info("SALVANDO ENTITY -> code: {}", entity.getCode());

        entity.setCode(rawMaterial.getCode());
        entity.setName(rawMaterial.getName());
        entity.setQuantityInStock(rawMaterial.getQuantityInStock());

        RawMaterialJpaEntity saved = rawMaterialJpaRepository.save(entity);

        log.info("SALVO NO BANCO -> code: {}", saved.getCode());

        return toDomain(saved);
    }

    @Override
    public Optional<RawMaterial> findById(Long id) {
        return rawMaterialJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<RawMaterial> findAll() {
        return rawMaterialJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        rawMaterialJpaRepository.deleteById(id);
    }

    private RawMaterialJpaEntity toJpa(RawMaterial rawMaterial) {
        RawMaterialJpaEntity entity = new RawMaterialJpaEntity();
        entity.setId(rawMaterial.getId());
        entity.setCode(rawMaterial.getCode());
        entity.setName(rawMaterial.getName());
        entity.setQuantityInStock(rawMaterial.getQuantityInStock());
        return entity;
    }

    private RawMaterial toDomain(RawMaterialJpaEntity  entity) {
        return new RawMaterial(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getQuantityInStock()
        );
    }

    @Override
    public boolean existsByCode(String code) {
        return rawMaterialJpaRepository.existsByCode(code);
    }

}