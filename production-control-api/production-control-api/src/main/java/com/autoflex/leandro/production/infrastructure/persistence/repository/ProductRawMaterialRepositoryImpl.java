package com.autoflex.leandro.production.infrastructure.persistence.repository;

import com.autoflex.leandro.production.application.mapper.*;
import com.autoflex.leandro.production.domain.entity.ProductRawMaterial;
import com.autoflex.leandro.production.domain.repository.ProductRawMaterialRepository;
import com.autoflex.leandro.production.infrastructure.persistence.entity.ProductRawMaterialJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRawMaterialRepositoryImpl implements ProductRawMaterialRepository {

    private final ProductRawMaterialJpaRepository productRawMaterialJpaRepository;
    private final JpaProductRepository productRepository;
    private final RawMaterialJpaRepository rawMaterialRepository;

    public ProductRawMaterialRepositoryImpl(
            ProductRawMaterialJpaRepository productRawMaterialJpaRepository,
            JpaProductRepository productRepository,
            RawMaterialJpaRepository rawMaterialRepository
    ) {
        this.productRawMaterialJpaRepository = productRawMaterialJpaRepository;
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Override
    public ProductRawMaterial save(ProductRawMaterial association) {

        ProductRawMaterialJpaEntity entity = new ProductRawMaterialJpaEntity();

        entity.setId(association.getId());

        entity.setProduct(
                productRepository.findById(
                        association.getProduct().getId()
                ).orElseThrow()
        );

        entity.setRawMaterial(
                rawMaterialRepository.findById(
                        association.getRawMaterial().getId()
                ).orElseThrow()
        );

        entity.setQuantityRequired(association.getQuantityRequired());

        ProductRawMaterialJpaEntity saved =
                productRawMaterialJpaRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<ProductRawMaterial> findById(Long id) {
        return productRawMaterialJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<ProductRawMaterial> findAll() {
        return productRawMaterialJpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        productRawMaterialJpaRepository.deleteById(id);
    }

    @Override
    public List<ProductRawMaterial> findByProductId(Long productId) {
        return productRawMaterialJpaRepository.findByProduct_Id(productId).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProductRawMaterial> findByRawMaterialId(Long rawMaterialId) {
        return productRawMaterialJpaRepository.findByRawMaterial_Id(rawMaterialId).stream().map(this::toDomain).collect(Collectors.toList());
    }

    private ProductRawMaterial toDomain(ProductRawMaterialJpaEntity entity) {
        return new ProductRawMaterial(
                entity.getId(),
                ProductMapper.toDomain(entity.getProduct()),
                RawMaterialMapper.toDomain(entity.getRawMaterial()),
                entity.getQuantityRequired()
        );
    }

    @Override
    public boolean existsByProductIdAndRawMaterialId(Long productId, Long rawMaterialId) {
        return productRawMaterialJpaRepository
                .existsByProduct_IdAndRawMaterial_Id(productId, rawMaterialId);
    }

}